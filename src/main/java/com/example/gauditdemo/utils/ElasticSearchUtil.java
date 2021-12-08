package com.example.gauditdemo.utils;

import com.example.gauditdemo.config.ESConfigClient;
import com.example.gauditdemo.dao.OperationDao;
import org.elasticsearch.action.bulk.*;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Frederic.Hu
 * @Description
 * @date 2021/12/06 10:45
 */
@Component
public class ElasticSearchUtil {

    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ESConfigClient esConfigClient;

    private BulkProcessor bulkProcessor;

    @Resource
    private OperationDao operationDao;

    @Value("${audit.index.prefix.env}")
    private String auditIndexPrefixEnv;

    @PostConstruct
    public void init() {
        BulkProcessor.Listener listener = new BulkProcessor.Listener() {
            @Override
            public void beforeBulk(long executionId, BulkRequest request) {
                //重写beforeBulk,在每次bulk request发出前执行,在这个方法里面可以知道在本次批量操作中有多少操作数
                int numberOfActions = request.numberOfActions();
                logger.info("同步数量 Executing bulk [{}] with {} requests", executionId, numberOfActions);
            }

            @Override
            public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {
                //重写afterBulk方法，每次批量请求结束后执行，可以在这里知道是否有错误发生。
                if (response.hasFailures()) {
                    logger.error("Bulk [{}] executed with failures,response = {}", executionId, response.buildFailureMessage());
                } else {
                    logger.info("写入成功 Bulk [{}] completed in {} milliseconds", executionId, response.getTook().getMillis());
                }
            }

            @Override
            public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
                //重写方法，如果发生错误就会调用。
                logger.error("写入失败 Failed to execute bulk", failure);
            }
        };
        //在这里调用build()方法构造bulkProcessor,在底层实际上是用了bulk的异步操作
        this.bulkProcessor = BulkProcessor.builder(((bulkRequest, bulkResponseActionListener) -> {
            esConfigClient.esClient().bulkAsync(bulkRequest, RequestOptions.DEFAULT, bulkResponseActionListener);
        }), listener)
                // 1000条数据请求执行一次bulk
                .setBulkActions(1000)
                // 3mb的数据刷新一次bulk
                .setBulkSize(new ByteSizeValue(3L, ByteSizeUnit.MB))
                // 并发请求数量, 0不并发, 1并发允许执行
                .setConcurrentRequests(1)
                // 固定1s必须刷新一次
                .setFlushInterval(TimeValue.timeValueSeconds(5L))
                // 重试5次，间隔100s
                .setBackoffPolicy(BackoffPolicy.constantBackoff(TimeValue.timeValueSeconds(100L), 5))
                .build();
    }

    @PreDestroy
    public void destroy() {
        try {
            bulkProcessor.awaitClose(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            logger.error("Failed to close bulkProcessor", e);
        }
        logger.info("bulkProcessor closed!");
    }

    /** 每2分钟调用一次 */
    @Scheduled(cron = "0 */2 * * * ?")
    public void insertIntoEs() {
        long startTime = System.currentTimeMillis();
        List<Map<String, Object>> mapList = operationDao.selectOperationAndChangeDate();
        logger.info("同步数据 tongBuSize:{}条", mapList.size());
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal= Calendar.getInstance();
        if (!mapList.isEmpty()) {
            mapList.forEach(item -> {
                try {
                    // 自动生成的operationtime自动映射成date类型
                    cal.setTime(sdFormat.parse(item.get("operationtime").toString()));
                    // 插入es数据时间相差8小时
                    cal.add(Calendar.HOUR_OF_DAY,+8);
                    item.replace("operationtime", cal.getTime());
                } catch (ParseException e) {
                    logger.error("Failed to convert time", e);
                }
                this.bulkProcessor.add(new IndexRequest().index(auditIndexPrefixEnv + item.get("indexsuffix").toString()).source(item, XContentType.JSON));
            });
        }
        System.out.println(" use time: " + (System.currentTimeMillis() - startTime) / 1000 + "s");
    }

}

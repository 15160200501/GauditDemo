package com.example.gauditdemo.utils;

import com.example.gauditdemo.config.ESConfigClient;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

/**
 * @author Frederic.Hu
 * @Description
 * @date 2021/12/16 10:19
 */
@Component
public class ESCommonUtils {

    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ESConfigClient esConfigClient;

    @PreDestroy
    public void destory() {
        try {
            esConfigClient.esClient().close();
        } catch (Exception e) {
            logger.error("关闭restHighLevelClient异常：", e);
        }
    }


    /**
     * @return org.elasticsearch.action.bulk.BulkProcessor
     * @description: 构建bulkProcessor接口 异步执行
     *
     */
    public BulkProcessor getBulkAsyncProcessor( ) {
        BulkProcessor bulkProcessor = null;
        try {
            BiConsumer<BulkRequest, ActionListener<BulkResponse>> consumer =
                    (bulkRequest, bulkResponseActionListener) -> esConfigClient.esClient().bulkAsync(
                            bulkRequest, RequestOptions.DEFAULT, bulkResponseActionListener);

            bulkProcessor = BulkProcessor.builder(consumer, this.getListener()
            ).setBulkActions(5000)  //  达到刷新的条数
                    .setBulkSize(new ByteSizeValue(100L, ByteSizeUnit.MB)) // 达到 刷新的大小
                    .setConcurrentRequests(10) // 并发请求数量, 0不并发, 1并发允许执行
                    .setFlushInterval(TimeValue.timeValueSeconds(1))  // 固定刷新的时间频率
                    .setBackoffPolicy(BackoffPolicy.constantBackoff(
                            TimeValue.timeValueSeconds(1L), 3)) // 重试补偿策略
                    .build();
        } catch (Exception e) {
            logger.error("构建BulkProcessor异常：", e);
            try {
                if (null != bulkProcessor) {
                    bulkProcessor.awaitClose(100L, TimeUnit.SECONDS);
                }
            } catch (Exception e1) {
                logger.error("关闭BulkProcessor异常", e1);
            }
        }
        return bulkProcessor;
    }

    /**
     * @return org.elasticsearch.action.bulk.BulkProcessor.Listener
     * @description: 构建监听器
     * @author:
     */
    BulkProcessor.Listener getListener() {
        return new BulkProcessor.Listener() {
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
    }

}

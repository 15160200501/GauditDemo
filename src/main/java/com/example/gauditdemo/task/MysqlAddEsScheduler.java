package com.example.gauditdemo.task;

import com.example.gauditdemo.dao.OperationDao;
import com.example.gauditdemo.utils.ESCommonUtils;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Frederic.Hu
 * @Description
 * @date 2021/12/15 10:32
 */
public class MysqlAddEsScheduler extends QuartzJobBean {

    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OperationDao operationDao;

    @Autowired
    private ESCommonUtils esCommonUtils;

    @Value("${audit.index.prefix.env}")
    private String auditIndexPrefixEnv;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        long startTime = System.currentTimeMillis();
        BulkProcessor bulkProcessor = null;
        List<Map<String, Object>> mapList = selectAll();
        logger.info("同步数据 tongBuSize:{}条", mapList.size());
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        bulkProcessor = esCommonUtils.getBulkAsyncProcessor();
        try {
            if (!mapList.isEmpty()) {
                for (Map<String, Object> item : mapList) {
                    try {
                        // 自动生成的operationtime自动映射成date类型
                        cal.setTime(sdFormat.parse(item.get("operationtime").toString()));
                        // 插入es数据时间相差8小时
                        cal.add(Calendar.HOUR_OF_DAY, +8);
                        item.replace("operationtime", cal.getTime());
                    } catch (ParseException e) {
                        logger.error("Failed to convert time:", e);
                    }
                    bulkProcessor.add(new IndexRequest().index(auditIndexPrefixEnv + item.get("indexsuffix").toString()).source(item, XContentType.JSON));
                }
            }
            // 刷新
            bulkProcessor.flush();
        } catch (Exception e) {
            logger.error("BulkProcessor，插入数据异常", e);
        } finally {
            try {
                assert bulkProcessor != null;
                boolean terminatedFlag = bulkProcessor.awaitClose(30L, TimeUnit.SECONDS);
                logger.info("terminatedFlag result:{}", terminatedFlag);
            } catch (Exception e) {
                logger.error("finally exception:", e);
            }
        }
        logger.info("tongbu use time: " + (System.currentTimeMillis() - startTime) + "ms");
    }

    @Transactional(readOnly = true)
    List<Map<String, Object>> selectAll() {
        return operationDao.selectOperationAndChangeDate();
    }


}

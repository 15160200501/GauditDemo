package com.example.gauditdemo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.gauditdemo.config.ESConfigClient;
import com.example.gauditdemo.entity.Operation;
import com.example.gauditdemo.dao.OperationDao;
import com.example.gauditdemo.service.OperationService;
import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * (Operation)表服务实现类
 *
 * @author makejava
 * @since 2021-12-02 11:32:49
 */
@Service
public class OperationServiceImpl implements OperationService {

    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OperationDao operationDao;

   /* @Resource
    private BulkProcessor bulkProcessor;*/

    @Autowired
    private ESConfigClient esConfigClient;

//    private BulkProcessor bulkProcessor;


    /**
     * 通过ID查询单条数据
     *
     * @param operationId 主键
     * @return 实例对象
     */
    @Override
    public Operation queryById(String operationId) {
        return this.operationDao.queryById(operationId);
    }

    /**
     * 分页查询
     *
     * @param operation 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    /*@Override
    public Page<Operation> queryByPage(Operation operation, PageRequest pageRequest) {
        long total = this.operationDao.count(operation);
        return new PageImpl<>(this.operationDao.queryAllByLimit(operation, pageRequest), pageRequest, total);
    }*/

    /**
     * 新增数据
     *
     * @param operation 实例对象
     * @return 实例对象
     */
    @Override
    public Operation insert(Operation operation) {
        this.operationDao.insert(operation);
        return operation;
    }

    /**
     * 修改数据
     *
     * @param operation 实例对象
     * @return 实例对象
     */
    @Override
    public Operation update(Operation operation) {
        this.operationDao.update(operation);
        return this.queryById(operation.getOperationId());
    }

    /**
     * 通过主键删除数据
     *
     * @param operationId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String operationId) {
        return this.operationDao.deleteById(operationId) > 0;
    }

    @Override
    public List<Map<String, Object>> getOperationAll() {
        return null;
    }

    /** 批量插入数据到es中 */
    @Override
    public void bulkInsert() {
//        RestHighLevelClient esClient = esConfigClient.esClient();
//        try {
//            System.out.println("项目开始启动！");
//            long startTime = System.currentTimeMillis();
//            List<Map<String, Object>> mapList = operationDao.selectOperationAndChangeDate();
//            if (!mapList.isEmpty()) {
//                mapList.forEach(item -> {
//                    System.out.println(item);
//                    bulkProcessor.add(new IndexRequest().index(item.get("indexSuffix").toString()).id(item.get("id").toString()).source(item, XContentType.JSON));
//                });
////                List<String> idsList = mapList.stream().map(x -> x.get("id").toString()).collect(Collectors.toList());
////                operationDao.insertSyncStatus(idsList);
//            }
//            //最后一次刷新时间
////            bulkProcessor.flush();
////            boolean terminatedFlag = bulkProcessor.awaitClose(30, TimeUnit.SECONDS);
////            logger.info(String.valueOf(terminatedFlag));
//            // 将数据刷新到es, 注意这一步执行后并不会立即生效，取决于bulkProcessor设置的刷新时间
//            System.out.println(" use time: " + (System.currentTimeMillis() - startTime) / 1000 + "s");
//            System.out.println("项目结束！");
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error(e.getMessage());
//        }
//        finally {
//            try {
//                // 30秒后关闭BulkProcessor
////                boolean terminatedFlag = bulkProcessor.awaitClose(30, TimeUnit.SECONDS);
////                esClient.close();
////                logger.info(String.valueOf(terminatedFlag));
//            } catch (Exception e) {
//                e.printStackTrace();
//                logger.error(e.getMessage());
//            }
//        }
    }


}

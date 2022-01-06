package com.example.gauditdemo.controller;

import com.example.gauditdemo.dao.OperationDao;
import com.example.gauditdemo.entity.Operation;
import com.example.gauditdemo.service.OperationService;
import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

/**
 * (Operation)表控制层
 *
 * @author makejava
 * @since 2021-12-02 11:32:46
 */
@RestController
@RequestMapping("operation")
public class OperationController {

    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 服务对象
     */
    @Autowired
    private OperationService operationService;

    /*@Autowired
    private ElasticSearchUtil elasticSearchUtil;*/

    /*@GetMapping
    public ResponseEntity<Page<Operation>> queryByPage(Operation operation, PageRequest pageRequest) {
        return ResponseEntity.ok(this.operationService.queryByPage(operation, pageRequest));
    }*/

    /*@GetMapping
    public String queryEs() {
        System.out.println("111");
        this.operationService.bulkInsert();
        return  "OK";
    }*/

    @GetMapping
    public String queryEs() {
        System.out.println("111");
//        elasticSearchUtil.insertIntoEs();
        return "OK";
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Operation> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.operationService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param operation 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Operation> add(Operation operation) {
        return ResponseEntity.ok(this.operationService.insert(operation));
    }

    /**
     * 编辑数据
     *
     * @param operation 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Operation> edit(Operation operation) {
        return ResponseEntity.ok(this.operationService.update(operation));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.operationService.deleteById(id));
    }

}


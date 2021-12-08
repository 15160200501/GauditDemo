package com.example.gauditdemo.service;

import com.example.gauditdemo.entity.Operation;

import java.util.List;
import java.util.Map;

/**
 * (Operation)表服务接口
 *
 * @author makejava
 * @since 2021-12-02 11:32:48
 */
public interface OperationService {

    /**
     * 通过ID查询单条数据
     *
     * @param operationId 主键
     * @return 实例对象
     */
    Operation queryById(String operationId);

    /**
     * 分页查询
     *
     * @param operation 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
//    Page<Operation> queryByPage(Operation operation, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param operation 实例对象
     * @return 实例对象
     */
    Operation insert(Operation operation);

    /**
     * 修改数据
     *
     * @param operation 实例对象
     * @return 实例对象
     */
    Operation update(Operation operation);

    /**
     * 通过主键删除数据
     *
     * @param operationId 主键
     * @return 是否成功
     */
    boolean deleteById(String operationId);

    /** 查询所有数据 */
    List<Map<String ,Object>> getOperationAll();

    /** 批量插入数据到es中 */
    void bulkInsert();

}

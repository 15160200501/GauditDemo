package com.example.gauditdemo.dao;

import com.example.gauditdemo.entity.Operation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 * (Operation)表数据库访问层
 *
 * @author makejava
 * @since 2021-12-02 11:32:46
 */
public interface OperationDao {

    /**
     * 通过ID查询单条数据
     *
     * @param operationId 主键
     * @return 实例对象
     */
    Operation queryById(String operationId);

    /**
     * 查询指定行数据
     *
     * @param operation 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
//    List<Operation> queryAllByLimit(Operation operation, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param operation 查询条件
     * @return 总行数
     */
    long count(Operation operation);

    /**
     * 新增数据
     *
     * @param operation 实例对象
     * @return 影响行数
     */
    int insert(Operation operation);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Operation> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Operation> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Operation> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Operation> entities);

    /**
     * 修改数据
     *
     * @param operation 实例对象
     * @return 影响行数
     */
    int update(Operation operation);

    /**
     * 通过主键删除数据
     *
     * @param operationId 主键
     * @return 影响行数
     */
    int deleteById(String operationId);

    /** 查询所有数据 */
    List<Map<String, Object>> selectOperationAll();

    /** 需要查询的两张表关联的数据 */
    List<Map<String, Object>> selectOperationAndChangeDate();

    /** 已同步的数据插入到data_sync_status这张表中 */
    void insertSyncStatus(@Param("entities") List<String> entities);

}


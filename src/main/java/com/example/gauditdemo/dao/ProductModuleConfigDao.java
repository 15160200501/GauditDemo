package com.example.gauditdemo.dao;

import com.example.gauditdemo.entity.ProductModuleConfig;
import com.example.gauditdemo.to.productModuleConfigNameTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (ProductModuleConfig)表数据库访问层
 *
 * @author makejava
 * @since 2021-12-30 19:18:32
 */
public interface ProductModuleConfigDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ProductModuleConfig queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param productModuleConfig 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
//    List<ProductModuleConfig> queryAllByLimit(ProductModuleConfig productModuleConfig, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param productModuleConfig 查询条件
     * @return 总行数
     */
    long count(ProductModuleConfig productModuleConfig);

    /**
     * 新增数据
     *
     * @param productModuleConfig 实例对象
     * @return 影响行数
     */
    int insert(ProductModuleConfig productModuleConfig);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ProductModuleConfig> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ProductModuleConfig> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ProductModuleConfig> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ProductModuleConfig> entities);

    /**
     * 修改数据
     *
     * @param productModuleConfig 实例对象
     * @return 影响行数
     */
    int update(ProductModuleConfig productModuleConfig);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    /** 查询childmodule所有不禁用的数据 (0:禁用  1：启用)*/
    List<productModuleConfigNameTO> selectChildModule();

    /** 查询module所有不禁用的数据 */
    List<productModuleConfigNameTO> selectModule();

    /** 查询product所有不禁用的数据 */
    List<productModuleConfigNameTO> selectProduct();

}


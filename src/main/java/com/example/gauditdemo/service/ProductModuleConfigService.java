package com.example.gauditdemo.service;

import com.example.gauditdemo.entity.ProductModuleConfig;

/**
 * (ProductModuleConfig)表服务接口
 *
 * @author makejava
 * @since 2021-12-30 19:18:33
 */
public interface ProductModuleConfigService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ProductModuleConfig queryById(String id);

    /**
     * 分页查询
     *
     * @param productModuleConfig 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
//    Page<ProductModuleConfig> queryByPage(ProductModuleConfig productModuleConfig, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param productModuleConfig 实例对象
     * @return 实例对象
     */
    ProductModuleConfig insert(ProductModuleConfig productModuleConfig);

    /**
     * 修改数据
     *
     * @param productModuleConfig 实例对象
     * @return 实例对象
     */
    ProductModuleConfig update(ProductModuleConfig productModuleConfig);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}

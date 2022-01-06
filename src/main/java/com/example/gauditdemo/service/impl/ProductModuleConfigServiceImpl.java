package com.example.gauditdemo.service.impl;

import com.example.gauditdemo.entity.ProductModuleConfig;
import com.example.gauditdemo.dao.ProductModuleConfigDao;
import com.example.gauditdemo.service.ProductModuleConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (ProductModuleConfig)表服务实现类
 *
 * @author makejava
 * @since 2021-12-30 19:18:33
 */
@Service("productModuleConfigService")
public class ProductModuleConfigServiceImpl implements ProductModuleConfigService {
    @Resource
    private ProductModuleConfigDao productModuleConfigDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ProductModuleConfig queryById(String id) {
        return this.productModuleConfigDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param productModuleConfig 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    /*@Override
    public Page<ProductModuleConfig> queryByPage(ProductModuleConfig productModuleConfig, PageRequest pageRequest) {
        long total = this.productModuleConfigDao.count(productModuleConfig);
        return new PageImpl<>(this.productModuleConfigDao.queryAllByLimit(productModuleConfig, pageRequest), pageRequest, total);
    }*/

    /**
     * 新增数据
     *
     * @param productModuleConfig 实例对象
     * @return 实例对象
     */
    @Override
    public ProductModuleConfig insert(ProductModuleConfig productModuleConfig) {
        this.productModuleConfigDao.insert(productModuleConfig);
        return productModuleConfig;
    }

    /**
     * 修改数据
     *
     * @param productModuleConfig 实例对象
     * @return 实例对象
     */
    @Override
    public ProductModuleConfig update(ProductModuleConfig productModuleConfig) {
        this.productModuleConfigDao.update(productModuleConfig);
        return this.queryById(productModuleConfig.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.productModuleConfigDao.deleteById(id) > 0;
    }
}

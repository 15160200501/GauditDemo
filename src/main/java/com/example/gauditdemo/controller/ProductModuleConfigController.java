package com.example.gauditdemo.controller;

import com.example.gauditdemo.dao.ProductModuleConfigDao;
import com.example.gauditdemo.entity.ProductModuleConfig;
import com.example.gauditdemo.service.ProductModuleConfigService;
import com.example.gauditdemo.to.productModuleConfigNameTO;
import com.example.gauditdemo.vo.ChangeDataCreateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (ProductModuleConfig)表控制层
 *
 * @author makejava
 * @since 2021-12-30 19:18:32
 */
@RestController
@RequestMapping("productModuleConfig")
public class ProductModuleConfigController {
    /**
     * 服务对象
     */
    @Resource
    private ProductModuleConfigService productModuleConfigService;

    @Resource
    private ProductModuleConfigDao productModuleConfigDao;

    /**
     * 分页查询
     *
     * @param productModuleConfig 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    /*@GetMapping
    public ResponseEntity<Page<ProductModuleConfig>> queryByPage(ProductModuleConfig productModuleConfig, PageRequest pageRequest) {
        return ResponseEntity.ok(this.productModuleConfigService.queryByPage(productModuleConfig, pageRequest));
    }*/

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<ProductModuleConfig> queryById(@PathVariable("id") String id) {
        return ResponseEntity.ok(this.productModuleConfigService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param productModuleConfig 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<ProductModuleConfig> add(ProductModuleConfig productModuleConfig) {
        return ResponseEntity.ok(this.productModuleConfigService.insert(productModuleConfig));
    }

    /**
     * 编辑数据
     *
     * @param productModuleConfig 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<ProductModuleConfig> edit(ProductModuleConfig productModuleConfig) {
        return ResponseEntity.ok(this.productModuleConfigService.update(productModuleConfig));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(String id) {
        return ResponseEntity.ok(this.productModuleConfigService.deleteById(id));
    }

    @PostMapping("audit/changedata")
    public void addChangeData(@RequestBody List<ChangeDataCreateVo> changeDatas) {
        List<productModuleConfigNameTO> productLists = productModuleConfigDao.selectProduct();
        List<productModuleConfigNameTO> moduleLists = productModuleConfigDao.selectModule();
        List<productModuleConfigNameTO> childModuleLists = productModuleConfigDao.selectChildModule();
        for (int i = 0; i < changeDatas.size(); i++) {
            ChangeDataCreateVo changeDataCreateVo = changeDatas.get(i);
            if (!StringUtils.isEmpty(changeDataCreateVo.getChildModule()) && childModuleLists.stream().anyMatch(str -> changeDataCreateVo.getChildModule().equals(str.getName()))) {
                // 塞到kafka里面
                System.out.println("childMocule存在并且没有被禁用");
            } else if (!StringUtils.isEmpty(changeDataCreateVo.getModule()) && moduleLists.stream().anyMatch(str -> changeDataCreateVo.getModule().equals(str.getName()))) {
                // 塞到kafka里面
                System.out.println("module存在并且没有被禁用");
            } else if (!StringUtils.isEmpty(changeDataCreateVo.getProduct()) && productLists.stream().anyMatch(str -> changeDataCreateVo.getProduct().equals(str.getName()))) {
                // 塞到kafka里面
                System.out.println("product存在并且没有被禁用");
            } else {
                // 什么都不操作
                System.out.println("传过来的数据产线、模块、子模块不存在或者被禁用");
            }
        }

    }

}


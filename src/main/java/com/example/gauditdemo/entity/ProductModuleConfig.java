package com.example.gauditdemo.entity;

import java.io.Serializable;

/**
 * (ProductModuleConfig)实体类
 *
 * @author makejava
 * @since 2021-12-30 19:18:32
 */
public class ProductModuleConfig implements Serializable {
    private static final long serialVersionUID = -76049334662707662L;
    
    private String id;
    
    private String type;
    
    private String name;
    
    private String parentId;
    
    private String code;
    
    private Integer disable;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getDisable() {
        return disable;
    }

    public void setDisable(Integer disable) {
        this.disable = disable;
    }

}


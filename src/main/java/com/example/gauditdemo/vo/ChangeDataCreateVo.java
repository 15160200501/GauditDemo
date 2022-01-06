package com.example.gauditdemo.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ChangeDataCreateVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private String operationId;
    private String dmlType;
    private String personId;
    private String personUserName;
    private String tenant;
    private String product;
    private String module;
    private String childModule;
    private Date changeTime;
    private String objectId;
    private List<AuditObjectKey> objectKey;
    private List<AuditItemVo> objectChangeData;

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getDmlType() {
        return dmlType;
    }

    public void setDmlType(String dmlType) {
        this.dmlType = dmlType;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonUserName() {
        return personUserName;
    }

    public void setPersonUserName(String personUserName) {
        this.personUserName = personUserName;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getChildModule() {
        return childModule;
    }

    public void setChildModule(String childModule) {
        this.childModule = childModule;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public List<AuditObjectKey> getObjectKey() {
        return objectKey;
    }

    public void setObjectKey(List<AuditObjectKey> objectKey) {
        this.objectKey = objectKey;
    }

    public List<AuditItemVo> getObjectChangeData() {
        return objectChangeData;
    }

    public void setObjectChangeData(List<AuditItemVo> objectChangeData) {
        this.objectChangeData = objectChangeData;
    }
}

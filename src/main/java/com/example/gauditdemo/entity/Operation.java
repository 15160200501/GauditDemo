package com.example.gauditdemo.entity;

import java.io.Serializable;

/**
 * (Operation)实体类
 *
 * @author makejava
 * @since 2021-12-02 11:32:47
 */
public class Operation implements Serializable {
    private static final long serialVersionUID = -82809356884198057L;
    
    private String operationId;
    
    private String masterId;
    
    private String operatorPersonId;
    
    private String operatorUsername;
    
    private String operatorPersonName;
    
    private String operatorEmployeeNo;
    
    private Long operationTime;
    
    private String operationDetail;
    
    private String tenant;
    
    private String operationDetailClasskey;
    
    private String operationDetailResourcekey;


    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getOperatorPersonId() {
        return operatorPersonId;
    }

    public void setOperatorPersonId(String operatorPersonId) {
        this.operatorPersonId = operatorPersonId;
    }

    public String getOperatorUsername() {
        return operatorUsername;
    }

    public void setOperatorUsername(String operatorUsername) {
        this.operatorUsername = operatorUsername;
    }

    public String getOperatorPersonName() {
        return operatorPersonName;
    }

    public void setOperatorPersonName(String operatorPersonName) {
        this.operatorPersonName = operatorPersonName;
    }

    public String getOperatorEmployeeNo() {
        return operatorEmployeeNo;
    }

    public void setOperatorEmployeeNo(String operatorEmployeeNo) {
        this.operatorEmployeeNo = operatorEmployeeNo;
    }

    public Long getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Long operationTime) {
        this.operationTime = operationTime;
    }

    public String getOperationDetail() {
        return operationDetail;
    }

    public void setOperationDetail(String operationDetail) {
        this.operationDetail = operationDetail;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getOperationDetailClasskey() {
        return operationDetailClasskey;
    }

    public void setOperationDetailClasskey(String operationDetailClasskey) {
        this.operationDetailClasskey = operationDetailClasskey;
    }

    public String getOperationDetailResourcekey() {
        return operationDetailResourcekey;
    }

    public void setOperationDetailResourcekey(String operationDetailResourcekey) {
        this.operationDetailResourcekey = operationDetailResourcekey;
    }

}


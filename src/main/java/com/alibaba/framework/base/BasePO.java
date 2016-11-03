package com.alibaba.framework.base;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by 马永龙 on 2016/9/10.
 */
public class BasePO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long rowVersion;
    private Boolean isDeleted;
    private Long createBy;
    private Timestamp creationTime;
    private Long lastUpdateBy;
    private Timestamp lastUpdateTime;

    public BasePO(){

    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getRowVersion() {
        return rowVersion;
    }

    public void setRowVersion(Long rowVersion) {
        this.rowVersion = rowVersion;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public Long getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(Long lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public Timestamp getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Timestamp lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}

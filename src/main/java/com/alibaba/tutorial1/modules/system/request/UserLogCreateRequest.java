package com.alibaba.tutorial1.modules.system.request;


import com.alibaba.framework.base.BaseRequest;
import java.util.Date;

/**
 * Created by 马永龙 on 2016/9/10.
 */
public class UserLogCreateRequest extends BaseRequest {

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 访问类型
     */
    private String visitType;

    /**
     * 访问对象的ID
     */
    private Long visitId;

    /**
     * 访问时间
     */
    private Date visitTime;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getVisitType() {
        return this.visitType;
    }

    public void setVisitType(String visitType) {
        this.visitType = visitType;
    }

    public Long getVisitId() {
        return this.visitId;
    }

    public void setVisitId(Long visitId) {
        this.visitId = visitId;
    }

    public Date getVisitTime() {
        return this.visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }


}

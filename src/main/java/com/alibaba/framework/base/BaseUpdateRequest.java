package com.alibaba.framework.base;

/**
 * Created by 马永龙 on 2016/9/22.
 */
public class BaseUpdateRequest extends BaseRequest{

    private Long rowVersion;

    public BaseUpdateRequest(){
        
    }

    public Long getRowVersion() {
        return rowVersion;
    }

    public void setRowVersion(Long rowVersion) {
        this.rowVersion = rowVersion;
    }
}

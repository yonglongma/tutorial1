package com.alibaba.framework.base;

import java.util.List;

/**
 * Created by 马永龙 on 2016/9/12.
 */
public class BaseGetAllListResponse<T> extends BaseResponse{
    private Long totalCount;
    private List<T> result;

    public BaseGetAllListResponse(){

    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}

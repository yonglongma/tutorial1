package com.alibaba.framework.base;

/**
 * Created by 马永龙 on 2016/9/12.
 */
public class BaseFindRequest<T extends Enum<T>> extends BaseRequest {
    private int pageNumber = 1;
    private int pageSize = 10;
    private Enum<T> sortKey;

    public BaseFindRequest(){

    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Enum<T> getSortKey() {
        return sortKey;
    }

    public void setSortKey(Enum<T> sortKey) {
        this.sortKey = sortKey;
    }
}

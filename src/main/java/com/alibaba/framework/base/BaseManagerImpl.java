package com.alibaba.framework.base;

import org.dozer.Mapper;

/**
 * Created by 马永龙 on 2016/9/21.
 */
public class BaseManagerImpl {
    private Mapper mapper;

    public BaseManagerImpl() {
    }

    public Mapper getMapper() {
        return mapper;
    }

    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }
}

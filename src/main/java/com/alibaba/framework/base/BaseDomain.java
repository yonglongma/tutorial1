package com.alibaba.framework.base;

import java.io.Serializable;

/**
 * Created by 马永龙 on 2016/10/3.
 */
public class BaseDomain implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long rewVersion;

    public BaseDomain(){

    }

    public Long getRewVersion() {
        return rewVersion;
    }

    public void setRewVersion(Long rewVersion) {
        this.rewVersion = rewVersion;
    }
}

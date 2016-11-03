package com.alibaba.framework.annotation;

import java.lang.annotation.*;

/**
 * Created by 马永龙 on 2016/9/12.
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ApiName {
    String value();
}

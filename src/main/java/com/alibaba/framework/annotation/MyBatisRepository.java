package com.alibaba.framework.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Created by 马永龙 on 2016/9/12.
 * 自定义注解,标识MyBatis的DAO,方便org.mybatis.spring.mapper.MapperScannerConfigurer的扫描
 * MyBatis是一个支持普通SQL查询,存储过程和高级映射的优秀持久层框架。
 * repository一般作为持久层的Dao的命名
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Component
public @interface MyBatisRepository {
    String value() default "";
}

package com.alibaba.tutorial1.modules.system.dal;

import com.alibaba.tutorial1.modules.system.domain.Passport;
import com.alibaba.tutorial1.modules.system.request.UserFindRequest;
import com.alibaba.framework.annotation.MyBatisRepository;
import com.alibaba.tutorial1.modules.system.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 马永龙 on 2016/9/10.
 */
@MyBatisRepository("UserMapper")
public interface UserMapper extends UserMapperAuto{

    /**
     * 高级查询对象列表
     *
     * @param request  请求对象
     * @param passport 用户护照
     * @return 实体对象列表集合
     */
    List<User> find(@Param("request") UserFindRequest request, @Param("passport") Passport passport);

    /**
     * 高级查询对象列表总数
     *
     * @param request  请求对象
     * @param passport 用户护照
     * @return 实体对象列表集合总数
     */
    long findCount(@Param("request") UserFindRequest request, @Param("passport") Passport passport);
}

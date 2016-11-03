/**
 * @(#)UserStatisticsMapper.java  
 *
 * Copyright (c) 2014-2014  苏州犀牛网络科技有限公司 版权所有
 * xiniunet. All rights reserved.
 *
 * This software is the confidential and proprietary
 * information of  xiniunet.
 * ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only
 * in accordance with the terms of the contract agreement
 * you entered into with xiniunet.
 */
package com.alibaba.tutorial1.modules.system.dal;

import com.alibaba.framework.annotation.MyBatisRepository;
import com.alibaba.tutorial1.modules.system.domain.Passport;
import com.alibaba.tutorial1.modules.system.po.UserStatisticsPO;
import com.alibaba.tutorial1.modules.system.request.UserStatisticsFindRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 马永龙 on 2016/10/3.
 */
@MyBatisRepository("Another.SystemUserStatisticsMapper")
public interface UserStatisticsMapper extends UserStatisticsMapperAuto {

    /**
     * 更新实体对象
     *
     * @param request 请求对象
     * @return 受影响的记录条数
     */
    long update(@Param("request") UserStatisticsPO request, @Param("passport") Passport passport);

    /**
     * 高级查询对象列表
     *
     * @param request 请求对象
     * @param passport 用户护照
     * @return 实体对象列表集合
     */
    List<UserStatisticsPO> find(@Param("request") UserStatisticsFindRequest request, @Param("passport") Passport passport);

    /**
     * 高级查询对象列表总数
     *
     * @param request 请求对象
     * @param passport 用户护照
     * @return 实体对象列表集合总数
     */
    long findCount(@Param("request") UserStatisticsFindRequest request, @Param("passport") Passport passport);

    /**
     * 更新用户的点赞数量
     */
    void updateLikeNumber(@Param("id") Long id, @Param("number") int number);

    /**
     * 更新用户的收藏数量
     */
    void updateCollectionNumber(@Param("id") Long id, @Param("number") int number);

    /**
     * 更新用户的评论数量
     */
    void updatePostNumber(@Param("id") Long id, @Param("number") int number);

}

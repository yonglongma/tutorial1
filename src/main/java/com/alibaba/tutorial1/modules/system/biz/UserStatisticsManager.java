/**
 * @(#)UserStatisticsManager.java
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
package com.alibaba.tutorial1.modules.system.biz;


import com.alibaba.tutorial1.modules.system.domain.Passport;
import com.alibaba.tutorial1.modules.system.request.*;
import com.alibaba.tutorial1.modules.system.response.*;

/**
 * Created by 马永龙 on 2016/10/3.
 */
public interface UserStatisticsManager {
    /**
     * 根据Id获取用户信息统计
     *
     * @param request 获取用户信息统计请求
     * @param passport 用户护照
     * @return 获取用户信息统计应答
     */
    UserStatisticsGetResponse get(UserStatisticsGetRequest request, Passport passport);

    /**
     * 高级查询用户信息统计
     *
     * @param request 高级查询用户信息统计请求
     * @param passport 用户护照
     * @return 高级查询应答
     */
    UserStatisticsFindResponse find(UserStatisticsFindRequest request, Passport passport);

    /**
     * 创建用户信息统计
     *
     * @param request 创建用户信息统计请求
     * @param passport 用户护照
     * @return 创建用户信息统计应答
     */
    UserStatisticsCreateResponse create(UserStatisticsCreateRequest request, Passport passport);

    /**
     * 更新用户信息统计
     *
     * @param request 更新用户信息统计请求
     * @param passport 用户护照
     * @return 更新用户信息统计应答
     */
    UserStatisticsUpdateResponse update(UserStatisticsUpdateRequest request, Passport passport);

    /**
     * 删除用户信息统计
     *
     * @param request 删除用户信息统计请求
     * @param passport 用户护照
     * @return 删除用户信息统计应答
     */
    UserStatisticsDeleteResponse delete(UserStatisticsDeleteRequest request, Passport passport);

}

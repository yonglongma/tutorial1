/**
 * @(#)UserStatisticsManagerImpl.java
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

import com.alibaba.framework.base.BaseManagerImpl;
import com.alibaba.framework.base.BaseResponse;
import com.alibaba.framework.exception.ErrorType;
import com.alibaba.tutorial1.modules.system.dal.UserStatisticsMapper;
import com.alibaba.tutorial1.modules.system.domain.Passport;
import com.alibaba.tutorial1.modules.system.domain.UserStatistics;
import com.alibaba.tutorial1.modules.system.po.UserStatisticsPO;
import com.alibaba.tutorial1.modules.system.request.*;
import com.alibaba.tutorial1.modules.system.response.*;
import com.alibaba.tutorial1.tool.Message;
import com.alibaba.tutorial1.tool.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 马永龙 on 2016/10/3.
 */
@Transactional
@Service("Another.SystemUserStatisticsManager")
public class UserStatisticsManagerImpl extends BaseManagerImpl implements UserStatisticsManager {



    @Autowired
    private UserStatisticsMapper userStatisticsMapper;

    /**
     * 根据Id获取用户信息统计
     *
     * @param request 获取用户信息统计请求
     * @param passport 用户护照
     * @return 获取用户信息统计应答
     */
    @Override
    @Transactional(readOnly = true)
    public UserStatisticsGetResponse get(UserStatisticsGetRequest request, Passport passport) {
        UserStatisticsPO entity = userStatisticsMapper.getById(request.getId(), passport);
        UserStatisticsGetResponse response = new UserStatisticsGetResponse();
        if (entity != null) {
            UserStatistics userStatistics = this.getMapper().map(entity, UserStatistics.class);
            response.setUserStatistics(userStatistics );
        } else {
            response.addError(ErrorType.EXPECTATION_NULL, Message.COMMON_GET_FAILURE);
        }
        return response;
    }

    /**
     * 高级查询用户信息统计
     *
     * @param request 高级查询用户信息统计请求
     * @param passport 用户护照
     * @return 高级查询应答
     */
    @Override
    @Transactional(readOnly = true)
    public UserStatisticsFindResponse find(UserStatisticsFindRequest request, Passport passport) {
        UserStatisticsFindResponse response = new UserStatisticsFindResponse();
        List<UserStatistics> modelList = new ArrayList<UserStatistics>();
        Long count = userStatisticsMapper.findCount(request, passport);
        if (count >0) {
            // 处理分页参数
            if (request.getPageSize() > 0) {
                //如果输入的页码大于实际的分页数，将页码设置为最后一页的页码
                int lastPageNumber = (int) ((count - 1) / request.getPageSize() + 1);
                if (request.getPageNumber() > lastPageNumber) {
                    request.setPageNumber(lastPageNumber);
                }
            }
    
            List<UserStatisticsPO> entityList = userStatisticsMapper.find(request, passport);
            for (UserStatisticsPO entity : entityList) {
                UserStatistics userStatistics = this.getMapper().map(entity, UserStatistics.class);
                modelList.add(userStatistics);
            }
        }
    
        response.setTotalCount(count);
        response.setResult(modelList);
        return response;
    }

    /**
     * 创建用户信息统计
     *
     * @param request 创建用户信息统计请求
     * @param passport 用户护照
     * @return 创建用户信息统计应答
     */
    @Override
    public UserStatisticsCreateResponse create(UserStatisticsCreateRequest request, Passport passport) {
        UserStatisticsPO entity = this.getMapper().map(request, UserStatisticsPO.class);
        long id = Sequence.getNewId();
        entity.setId(id);
    
        UserStatisticsCreateResponse response = new UserStatisticsCreateResponse();
    
        /* 先检查关键数据是否有重复，在检查通过后才能做插入操作 */
        checkValidate(entity,passport,response);
    
        if (1 == userStatisticsMapper.insert(entity, passport)) {
            response.setId(id);
        } else {
            response.addError(ErrorType.EXPECTATION_NULL, Message.COMMON_CREATE_FAILURE);
        }
        return response;
    }
    
    /**
     * 更新用户信息统计
     *
     * @param request 更新用户信息统计请求
     * @param passport 用户护照
     * @return 更新用户信息统计应答
     */
    @Override
    public UserStatisticsUpdateResponse update(UserStatisticsUpdateRequest request, Passport passport) {
        UserStatisticsPO entity = this.getMapper().map(request, UserStatisticsPO.class);
    
        UserStatisticsUpdateResponse response = new UserStatisticsUpdateResponse();
        Long result=userStatisticsMapper.update(entity, passport);
        if (result != 1) {
            response.addError(ErrorType.BUSINESS_ERROR, Message.COMMON_UPDATE_FAILURE);
            return response;
        }
        response.setResult(result);
        return response;
    }
    
    /**
     * 删除用户信息统计
     *
     * @param request 删除用户信息统计请求
     * @param passport 用户护照
     * @return 删除用户信息统计应答
     */
    @Override
    public UserStatisticsDeleteResponse delete(UserStatisticsDeleteRequest request, Passport passport) {
        UserStatisticsDeleteResponse response = new UserStatisticsDeleteResponse();
         Long result= userStatisticsMapper.delete(request.getId(), passport);
         response.setResult(result);
        return response;
    }

    /**
     * 验证对象
     * @param userStatistics 用户信息统计
     * @param passport 用户护照
     */
    private void checkValidate(UserStatisticsPO userStatistics, Passport passport, BaseResponse response) {
        // TODO
    }
}
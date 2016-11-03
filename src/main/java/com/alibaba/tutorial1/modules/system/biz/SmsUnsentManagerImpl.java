/**
 * @(#)SmsUnsentManagerImpl.java Copyright (c) 2014-2014  苏州犀牛网络科技有限公司 版权所有
 * xiniunet. All rights reserved.
 * <p/>
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
import com.alibaba.tutorial1.modules.system.dal.SmsUnsentMapper;
import com.alibaba.tutorial1.modules.system.domain.Passport;
import com.alibaba.tutorial1.modules.system.po.SmsUnsentPO;
import com.alibaba.tutorial1.modules.system.request.SmsUnsentCreateRequest;
import com.alibaba.tutorial1.modules.system.request.SmsUnsentDeleteRequest;
import com.alibaba.tutorial1.modules.system.request.SmsUnsentUpdateRequest;
import com.alibaba.tutorial1.modules.system.response.SmsUnsentCreateResponse;
import com.alibaba.tutorial1.modules.system.response.SmsUnsentDeleteResponse;
import com.alibaba.tutorial1.modules.system.response.SmsUnsentUpdateResponse;
import com.alibaba.tutorial1.tool.Message;
import com.alibaba.tutorial1.tool.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 马永龙 on 2016/10/3.
 */
@Transactional
@Service("FoundationSmsUnsentManager")
public class SmsUnsentManagerImpl extends BaseManagerImpl implements SmsUnsentManager {

    @Autowired
    private SmsUnsentMapper smsUnsentMapper;

    /**
     * 创建手机消息通知表(未发送的)
     *
     * @param request 创建手机消息通知表(未发送的)请求
     * @param passport 用户护照
     * @return 创建手机消息通知表(未发送的)应答
     */
    @Override
    public SmsUnsentCreateResponse create(SmsUnsentCreateRequest request, Passport passport) {
        SmsUnsentPO entity = this.getMapper().map(request, SmsUnsentPO.class);
        long id = Sequence.getNewId();
        entity.setId(id);

        SmsUnsentCreateResponse response = new SmsUnsentCreateResponse();

    /* 先检查关键数据是否有重复，在检查通过后才能做插入操作 */
        checkValidate(entity, passport, response);

        if (1 == smsUnsentMapper.insert(entity, passport)) {
            response.setId(id);
        } else {
            response.addError(ErrorType.EXPECTATION_NULL, Message.COMMON_CREATE_FAILURE);
        }
        return response;
    }


    /**
     * 更新手机消息通知表(未发送的)
     *
     * @param request 更新手机消息通知表(未发送的)请求
     * @param passport 用户护照
     * @return 更新手机消息通知表(未发送的)应答
     */
    @Override
    public SmsUnsentUpdateResponse update(SmsUnsentUpdateRequest request, Passport passport) {
        SmsUnsentPO entity = this.getMapper().map(request, SmsUnsentPO.class);

        SmsUnsentUpdateResponse response = new SmsUnsentUpdateResponse();
        Long result = smsUnsentMapper.update(entity, passport);
        if (result != 1) {
            response.addError(ErrorType.BUSINESS_ERROR, Message.COMMON_UPDATE_FAILURE);
            return response;
        }
        response.setResult(result);
        return response;
    }


    /**
     * 删除手机消息通知表(未发送的)
     *
     * @param request 删除手机消息通知表(未发送的)请求
     * @param passport 用户护照
     * @return 删除手机消息通知表(未发送的)应答
     */
    @Override
    public SmsUnsentDeleteResponse delete(SmsUnsentDeleteRequest request, Passport passport) {
        SmsUnsentDeleteResponse response = new SmsUnsentDeleteResponse();
        Long result = smsUnsentMapper.delete(request.getId(), passport);
        response.setResult(result);
        return response;
    }

    /**
     * 验证对象
     * @param smsUnsent 手机消息通知表(未发送的)
     * @param passport 用户护照
     */
    private void checkValidate(SmsUnsentPO smsUnsent, Passport passport, BaseResponse response) {
        // TODO

    }


}

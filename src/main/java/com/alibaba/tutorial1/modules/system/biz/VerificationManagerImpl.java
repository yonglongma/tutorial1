/**
 * @(#)VerificationManagerImpl.java Copyright (c) 2014-2014  苏州犀牛网络科技有限公司 版权所有
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
import com.alibaba.framework.exception.ErrorType;
import com.alibaba.tutorial1.modules.system.dal.VerificationMapper;
import com.alibaba.tutorial1.modules.system.domain.Passport;
import com.alibaba.tutorial1.modules.system.po.VerificationPO;
import com.alibaba.tutorial1.modules.system.request.SmsSendRequest;
import com.alibaba.tutorial1.modules.system.request.VerificationCheckRequest;
import com.alibaba.tutorial1.modules.system.request.VerificationCreateRequest;
import com.alibaba.tutorial1.modules.system.request.VerificationFindRequest;
import com.alibaba.tutorial1.modules.system.response.VerificationCheckResponse;
import com.alibaba.tutorial1.modules.system.response.VerificationCreateResponse;
import com.alibaba.tutorial1.tool.Message;
import com.alibaba.tutorial1.tool.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by 马永龙 on 2016/10/3.
 */
@Transactional
@Service("AnotherVerificationManager")
public class VerificationManagerImpl extends BaseManagerImpl implements VerificationManager {


    @Autowired
    private VerificationMapper verificationMapper;
    @Autowired
    private SmsManager smsManager;

    /**
     * 创建验证码
     *
     * @param request  创建验证码请求
     * @return 创建验证码应答
     */
    @Override
    public VerificationCreateResponse create(VerificationCreateRequest request) {
        VerificationCreateResponse response = new VerificationCreateResponse();

        Passport passport = new Passport();
        if(request.getUserId() != null) {
            passport.setUserId(request.getUserId());
        } else {
            passport.setUserId(0L);
        }

        // 删除该手机号其它的验证码
        VerificationFindRequest findRequest = new VerificationFindRequest();
        findRequest.setMobile(request.getMobile());
        findRequest.setPageSize(0);
        List<VerificationPO> poList = verificationMapper.find(findRequest, passport);
        List<Long> ids = new ArrayList<Long>();
        for(VerificationPO po : poList) {
            ids.add(po.getId());
        }
        if( !ids.isEmpty()) {
            verificationMapper.deleteBatch(ids, passport);
        }

        // 准备新的验证码的参数
        VerificationPO entity = this.getMapper().map(request, VerificationPO.class);
        long id = Sequence.getNewId();
        String code = (id + System.currentTimeMillis() * 7) % 1000000 + "";
        while(code.length() < 6) {
            code = "0" + code;
        }
        // 发送短信验证码
        String sms = "【AnotherU】您的验证码为" + code + "，请在一小时内使用。";
        SmsSendRequest sendRequest = new SmsSendRequest();
        sendRequest.setBusinessType("REGISTER_CODE");
        sendRequest.setBusinessId(id);
        sendRequest.setMobilePhone(request.getMobile());
        sendRequest.setMessageText(sms);
        sendRequest.setMessageTime(new Date());
        sendRequest.setSendTime(new Date());
        smsManager.send(sendRequest, passport);

        entity.setId(id);
        entity.setCode(code);
        // 设定验证时间
        entity.setActiveTime(new Date());
        // 设定失效时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR, 1);
        entity.setInactiveTime(calendar.getTime());

        if(1 == verificationMapper.insert(entity, passport)) {
            response.setCode(code);
        } else {
            response.addError(ErrorType.EXPECTATION_NULL, Message.COMMON_CREATE_FAILURE);
        }
        return response;
    }

    /**
     * 检验验证码
     *
     * @param request  检验验证码请求
     * @return 检验验证码应答
     */
    @Override
    public VerificationCheckResponse check(VerificationCheckRequest request) {
        VerificationCheckResponse response = new VerificationCheckResponse();
        response.setResult(false);
        Passport passport = new Passport();

        VerificationFindRequest findRequest = this.getMapper().map(request, VerificationFindRequest.class);
        List<VerificationPO> verificationList = verificationMapper.find(findRequest, passport);
        if(verificationList.isEmpty()) {
            response.addError("code", "您填写的验证码不正确");
            return response;
        } else {
            VerificationPO po = verificationList.get(0);
            Long time = new Date().getTime();
            if(po.getActiveTime().getTime() <= time && po.getInactiveTime().getTime() >= time) {
                response.setResult(true);
            } else {
                response.addError("code", "验证码已经过期,请您重新获取");
            }
            verificationMapper.delete(po.getId(), passport);
        }
        return response;
    }
}

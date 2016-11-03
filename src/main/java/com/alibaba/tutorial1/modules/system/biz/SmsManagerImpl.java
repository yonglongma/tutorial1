/**
 * @(#)SmsManagerImpl.java Copyright (c) 2014-2014  苏州犀牛网络科技有限公司 版权所有
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
import com.alibaba.tutorial1.modules.system.dal.SmsMapper;
import com.alibaba.tutorial1.modules.system.domain.Passport;
import com.alibaba.tutorial1.modules.system.po.SmsPO;
import com.alibaba.tutorial1.modules.system.request.*;
import com.alibaba.tutorial1.modules.system.response.*;
import com.alibaba.tutorial1.tool.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by 马永龙 on 2016/10/3.
 */
@Transactional
@Service("FoundationSmsManager")
public class SmsManagerImpl extends BaseManagerImpl implements SmsManager {

    @Autowired
    private SmsUnsentManager smsUnsentManager;
    @Autowired
    private SmsMapper smsMapper;

    /**
     * 创建手机短信通知表
     *
     * @param request 创建手机短信通知表请求
     * @param passport 用户护照
     * @return 创建手机短信通知表应答
     */
    @Override
    public SmsCreateResponse create(SmsCreateRequest request, Passport passport) {
        SmsPO entity = this.getMapper().map(request, SmsPO.class);
        long id;
        if(request.getId()!=null){
            id = request.getId();
        }else {
            id = Sequence.getNewId();
        }

        entity.setId(id);
        SmsCreateResponse response = new SmsCreateResponse();

    /* 先检查关键数据是否有重复，在检查通过后才能做插入操作 */
        checkValidate(entity, passport, response);

        if (1 == smsMapper.insert(entity, passport)) {
            response.setId(id);
        } else {
            response.addError(ErrorType.EXPECTATION_NULL, Message.COMMON_CREATE_FAILURE);
        }
        return response;
    }

     /**
     * 发送短信
     *
     * @param request  发送短信请求
     * @param passport 用户护照
     * @return 发送短信应答
     */
    @Override
    public SmsSendResponse send(SmsSendRequest request, Passport passport) {
        SmsSendResponse response = new SmsSendResponse();
        Long id = Sequence.getNewId();
        String smsApiUrl = "http://api.sms/cn/mt";
        try {
            Map<String, String> smsParams = new HashMap<String, String>();
            String uid = BaseUtil.getSmsApiUid();
            String pwd = BaseUtil.getSmsApiPwd();
            smsParams.put("uid", uid);
            smsParams.put("pwd", EncryptUtil.MD5(pwd + uid));
            smsParams.put("mobile", request.getMobilePhone());
            smsParams.put("content", URLEncoder.encode(request.getMessageText(), "GBK"));//异常

            for (int i = 0; i < 3; i++) {
                String status = callSmsApi(smsApiUrl, smsParams);//异常
                if (status.equals("stat=100")) {
                    SmsCreateRequest smsCreateRequest = this.getMapper().map(request, SmsCreateRequest.class);
                    smsCreateRequest.setId(id);
                    smsCreateRequest.setSenderUserId(passport.getUserId());
                    smsCreateRequest.setSendTime(new Date());
                    smsCreateRequest.setMessageTime(new Date());
                    SmsCreateResponse smsCreateResponse = create(smsCreateRequest, passport);
                    if (smsCreateResponse.hasError()) {
                        response.addErrors(smsCreateResponse.getErrors());
                        return response;
                    }
                    // 如果已经有失败过，则将Unsent表中的记录删除
                    if (i > 1) {
                        SmsUnsentDeleteRequest smsUnsentDeleteRequest = new SmsUnsentDeleteRequest();
                        smsUnsentDeleteRequest.setId(id);
                        SmsUnsentDeleteResponse smsUnsentDeleteResponse = smsUnsentManager.delete(smsUnsentDeleteRequest, passport);
                        if (smsUnsentDeleteResponse.hasError()) {
                            response.addErrors(smsUnsentDeleteResponse.getErrors());
                            return response;
                        }
                    }
                    break;
                }
                // 如果是第一次失败，那么插入unsent表
                else if (i == 0) {
                    SmsUnsentCreateRequest smsUnsentCreateRequest = new SmsUnsentCreateRequest();
                    smsUnsentCreateRequest.setSendCount(1);
                    smsUnsentCreateRequest.setId(id);
                    SmsUnsentCreateResponse smsUnsentCreateResponse = smsUnsentManager.create(smsUnsentCreateRequest, passport);
                    if (smsUnsentCreateResponse.hasError()) {
                        response.addErrors(smsUnsentCreateResponse.getErrors());
                        return response;
                    }
                }
                // 如果不是第一次失败，那么更新unsent表
                else {
                    SmsUnsentUpdateRequest smsUnsentUpdateRequest = new SmsUnsentUpdateRequest();
                    smsUnsentUpdateRequest.setSendCount(i + 1);
                    smsUnsentUpdateRequest.setId(id);
                    SmsUnsentUpdateResponse smsUnsentUpdateResponse = smsUnsentManager.update(smsUnsentUpdateRequest, passport);
                    if (smsUnsentUpdateResponse.hasError()) {
                        response.addErrors(smsUnsentUpdateResponse.getErrors());
                        return response;
                    }
                }
            }
        } catch (Exception e) {
            response.addError(ErrorType.SYSTEM_ERROR, e.getMessage());
        }
        response.setId(id);
        return response;
    }

    private String callSmsApi(String url,Map<String,String> params) throws Exception{
        String responseString = HttpUtil.get(url, params);
        String[] resultList = responseString.split("&");
        String status = resultList[1];
        return status;
    }

    /**
     * 验证对象
     * @param sms 手机短信通知表
     * @param passport 用户护照
     */
    private void checkValidate(SmsPO sms, Passport passport, BaseResponse response) {
        // TODO

    }


}

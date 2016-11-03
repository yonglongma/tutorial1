package com.alibaba.tutorial1.modules.system.biz;

import com.alibaba.framework.base.BaseManagerImpl;
import com.alibaba.framework.exception.ErrorType;
import com.alibaba.tutorial1.modules.system.domain.Passport;
import com.alibaba.tutorial1.modules.system.po.PassportPO;
import com.alibaba.tutorial1.modules.system.request.PassportCreateRequest;
import com.alibaba.tutorial1.modules.system.request.PassportFindRequest;
import com.alibaba.tutorial1.modules.system.request.PassportGetRequest;
import com.alibaba.tutorial1.modules.system.request.PassportUpdateRequest;
import com.alibaba.tutorial1.modules.system.response.PassportCreateResponse;
import com.alibaba.tutorial1.tool.Message;
import com.alibaba.tutorial1.tool.Sequence;
import com.alibaba.tutorial1.modules.system.dal.PassportMapper;
import com.alibaba.tutorial1.modules.system.response.PassportGetResponse;
import com.alibaba.tutorial1.modules.system.response.PassportUpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 马永龙 on 2016/9/22.
 */
@Transactional
@Service("PassportManager")
public class PassportManagerImpl extends BaseManagerImpl implements PassportManager {

    @Autowired
    private PassportMapper passportMapper;
    /**
     * 根据Id获取用户护照
     *
     * @param request 获取用户护照请求
     * @return 获取用户护照应答
     */
    @Override
    public PassportGetResponse get(PassportGetRequest request) {
        PassportGetResponse response = new PassportGetResponse();
        PassportPO entity = passportMapper.getById(request.getId());
        if(entity != null){
            Passport passport = this.getMapper().map(entity,Passport.class);
            response.setPassport(passport);
        }else {
            response.addError(ErrorType.EXPECTATION_NULL, Message.COMMON_GET_FAILURE);
        }
        return response;
    }

    /**
     * 创建用户护照
     *
     * @param request  创建用户护照请求
     * @param passport 用户护照
     * @return 创建用户护照应答
     */
    @Override
    public PassportCreateResponse create(PassportCreateRequest request, Passport passport) {
        PassportCreateResponse response = new PassportCreateResponse();

        PassportPO entity = this.getMapper().map(request,PassportPO.class);
        if(entity.getId() == null){
            entity.setId(Sequence.getNewId());
        }
        if(1== passportMapper.insert(entity)){
            response.setId(entity.getId());
        }else{
            response.addError(ErrorType.EXPECTATION_NULL, Message.COMMON_CREATE_FAILURE);
        }
        return response;
    }

    /**
     * 更新用户护照
     *
     * @param request  更新用户护照请求
     * @param passport 用户护照
     * @return 更新用户护照应答
     */
    @Override
    public PassportUpdateResponse update(PassportUpdateRequest request, Passport passport) {
        PassportUpdateResponse response = new PassportUpdateResponse();

        PassportPO entity = this.getMapper().map(request,PassportPO.class);
        Long result = passportMapper.update(entity, passport);
        if(result != 1) {
            response.addError(ErrorType.BUSINESS_ERROR, Message.COMMON_UPDATE_FAILURE);
            return response;
        }
        response.setResult(result);
        return response;
    }

    /**
     * 批量注销用户护照
     *退出登录或修改密码需注销其他浏览器端的护照
     * @param passport 用户护照
     * @return 更新用户护照应答
     */
    @Override
    public PassportUpdateResponse invokeBatch(Passport passport) {
        PassportUpdateResponse response = new PassportUpdateResponse();
        Long result = 0L;
        if(passport != null){
            PassportFindRequest request = new PassportFindRequest();
            request.setPageSize(0);
            request.setUserId(passport.getUserId());
            List<PassportPO> passportPOList = passportMapper.find(request, passport);
            List<Long> ids = new ArrayList<Long>();
            //至少有多个护照
            if(passportPOList.size() > 1){
                for(PassportPO passportPO :passportPOList){
                    //过滤当前使用的护照
                    if(!passportPO.getId().equals(passport.getId())){
                        //过滤掉过期护照
                        if(passportPO.getExpireTime().getTime() > new Date().getTime()){
                            ids.add(passportPO.getId());
                        }
                    }
                }
                if(ids.size() >0){
                    result = passportMapper.invokeBatch(ids,passport);
                }
            }
        }
        response.setResult(result);
        return response;
    }
}

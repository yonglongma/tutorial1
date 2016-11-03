package com.alibaba.tutorial1.modules.system.biz;

import com.alibaba.framework.base.BaseManagerImpl;
import com.alibaba.tutorial1.modules.system.domain.UserPassword;
import com.alibaba.tutorial1.modules.system.enumication.IdentityTypeEnum;
import com.alibaba.tutorial1.modules.system.request.*;
import com.alibaba.tutorial1.modules.system.response.*;
import com.alibaba.tutorial1.tool.EncryptUtil;
import com.alibaba.tutorial1.tool.Message;
import com.alibaba.tutorial1.tool.Sequence;
import com.alibaba.framework.exception.ErrorType;
import com.alibaba.tutorial1.modules.system.dal.UserMapper;
import com.alibaba.tutorial1.modules.system.domain.Passport;
import com.alibaba.tutorial1.modules.system.domain.User;
import com.alibaba.tutorial1.modules.system.po.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 马永龙 on 2016/9/12.
 */
@Transactional
@Service("UserManager")
public class UserManagerImpl extends BaseManagerImpl implements UserManager {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserPasswordManager userPasswordManager;
    @Autowired
    private PassportManager passportManager;
    @Autowired
    private UserStatisticsManager userStatisticsManager;

    /**
     * 根据Id获取用户表
     *
     * @param request  获取用户表请求
     * @param passport 用户护照
     * @return 获取用户表应答
     */
    @Override
    @Transactional(readOnly = true)
    public UserGetResponse get(UserGetRequest request, Passport passport) {
        User entity = userMapper.get(request.getId(), passport);
        UserGetResponse response = new UserGetResponse();
        if(entity != null) {
            User user = this.getMapper().map(entity, User.class);
            response.setUser(user);
        } else {
            response.addError(ErrorType.EXPECTATION_NULL, Message.COMMON_GET_FAILURE);
        }
        return response;
    }

    @Override
    public UserCreateResponse create(UserCreateRequest request, Passport passport) {
        UserCreateResponse response = new UserCreateResponse();
        //判断手机号是否已注册
        if(request.getMobile() != null && !request.getMobile().isEmpty()){
            if(request.getMobileBind() == null){
                request.setMailBind(false);
            }
            UserFindRequest findRequest = new UserFindRequest();
            findRequest.setMobile(request.getMobile());
            UserFindResponse findResponse = this.find(findRequest,passport);
            if(!findResponse.getResult().isEmpty()){
                response.addError(ErrorType.BUSINESS_ERROR,"已注册的手机号");
                return response;
            }
            //手机格式匹配
            Pattern p = Pattern.compile("^1[34578][0-9]{9}$");
            Matcher m = p.matcher(request.getMobile());
            if(!m.find()){
                response.addError(ErrorType.INVALID_PARAMETER,"手机号码格式不正确");
                return response;
            }
        }
        //创建用户
        UserPO entity = this.getMapper().map(request,UserPO.class);
        if(entity.getId() == null){
            Long id = Sequence.getNewId();
            entity.setId(id);
        }
        if(null != request.getName()){
            entity.setName(request.getName());
        }
        if(1 == userMapper.insert(entity,passport)){
            //创建用户统计
            UserStatisticsCreateRequest statisticsCreateRequest = new UserStatisticsCreateRequest();
            statisticsCreateRequest.setUserId(entity.getId());
            statisticsCreateRequest.setCollectionCount(0);
            statisticsCreateRequest.setLikeCount(0);
            statisticsCreateRequest.setPostCount(0);
            statisticsCreateRequest.setUnreadMessageCount(0);
            userStatisticsManager.create(statisticsCreateRequest,passport);
            response.setId(entity.getId());
        }else{
            response.addError(ErrorType.EXPECTATION_NULL, Message.COMMON_CREATE_FAILURE);
        }
        //创建密码
        UserPasswordCreateRequest passwordCreateRequest = new UserPasswordCreateRequest();
        passwordCreateRequest.setId(entity.getId());
        //md5加密后的密码
        passwordCreateRequest.setLoginPassword(request.getPassword());
        userPasswordManager.create(passwordCreateRequest,passport);

        return response;
    }

    @Override
    public UserFindResponse find(UserFindRequest request, Passport passport) {
        UserFindResponse response = new UserFindResponse();
        List<User> modelList = new ArrayList<User>();
        Long count = userMapper.findCount(request,passport);
        if(count > 0){
            //处理分页
            if(request.getPageSize() > 0){
                //如果输入的页码大于实际的分页数，将页码设置为最后一页的页码
                int lastPageNumber = (int) ((count - 1) / request.getPageSize() + 1);
                if(request.getPageNumber() > lastPageNumber) {
                    request.setPageNumber(lastPageNumber);
                }
            }
            modelList = userMapper.find(request, passport);
        }
        response.setTotalCount(count);
        response.setResult(modelList);
        return response;
    }

    /**
     * 注册
     * * @param request 注册参数
     * @return 新的用户信息
     */
    @Override
    public RegisterResponse register(RegisterRequest request){
        RegisterResponse response = new RegisterResponse();
        Passport passport = new Passport();
        passport.setUserId(0L);

        //创建用户
        UserCreateRequest createRequest = new UserCreateRequest();
        createRequest.setMobile(request.getMobile());
        createRequest.setMobileBind(true);
        createRequest.setName(request.getName());
        createRequest.setPassword(request.getPassword());
        UserCreateResponse createResponse = this.create(createRequest,passport);
        if(createResponse.hasError()){
            response.addErrors(createResponse.getErrors());
            return response;
        }

        //创建成功后为其自动登录
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setAccount(request.getMobile());
        loginRequest.setPassword(request.getPassword());
        loginRequest.setClientIp("127.0.0.1");
        loginRequest.setClientType("AUTO");
        LoginResponse loginResponse = this.login(loginRequest);

        response.setPassport(loginResponse.getPassport());
        response.setUser(loginResponse.getUser());
        return response;
    }

    /**
     * 登录
     *
     * @param request 登录参数
     * @return 登录的结果
     */
    @Override
    public LoginResponse login(LoginRequest request) {
        LoginResponse response = new LoginResponse();
        Passport passport = new Passport();

        User user = null;
        //根据手机号查询用户
        UserFindRequest findRequest = new UserFindRequest();
        findRequest.setMobile(request.getAccount());
        UserFindResponse findResponse = find(findRequest,passport);
        if(findResponse.getResult().size() > 1){
            response.addError(ErrorType.SYSTEM_ERROR,"系统错误，重复的手机号码");
            return response;
        }else if(findResponse.getResult().size() == 1){
            user = findResponse.getResult().get(0);
        }

        //查询密码
        UserPasswordGetRequest getRequest = new UserPasswordGetRequest();
        getRequest.setId(user.getId());
        UserPasswordGetResponse getResponse = userPasswordManager.get(getRequest,passport);
        UserPassword userPassword = getResponse.getUserPassword();

        if(userPassword == null){
            response.addError(ErrorType.INVALID_PARAMETER,"用户密码不存在");
            return response;
        }

        //加密
        String _password = EncryptUtil.SHA1(request.getPassword().toUpperCase() + userPassword.getLoginSalt().toUpperCase()).toUpperCase();

        // 如果密码不匹配
        if(!_password.equals(userPassword.getLoginPassword())) {
            response.addError(ErrorType.INVALID_PARAMETER, "密码错误");
            return response;
        }


        // 创建passport
        PassportCreateRequest passportCreateRequest = new PassportCreateRequest();
        passportCreateRequest.setUserId(user.getId());
        passportCreateRequest.setIdentityType(IdentityTypeEnum.COMMON);
        passportCreateRequest.setIssueTime(new Date());
        // 默认七天有效
        passportCreateRequest.setExpireTime(new Date(new Date().getTime() + 7 * 24 * 60 * 60 * 1000));
        passportCreateRequest.setIssueIp(request.getClientIp());
        passportCreateRequest.setIssueClient(request.getClientType());
        PassportCreateResponse passportCreateResponse = passportManager.create(passportCreateRequest, passport);

        // 获取passport
        PassportGetRequest passportGetRequest = new PassportGetRequest();
        passportGetRequest.setId(passportCreateResponse.getId());
        passport = passportManager.get(passportGetRequest).getPassport();
        response.setPassport(passport);
        response.setNeedChangeLogin(userPassword.getNeedChangeLogin());
        response.setUser(user);

        return response;
    }

    /**
     * 注销
     *
     * @param request  注销请求
     * @param passport 护照信息
     * @return  注销的结果
     */
    @Override
    public LogoutResponse logout(LogoutRequest request, Passport passport) {
        LogoutResponse response = new LogoutResponse();

        if (passport != null){
            PassportGetRequest getRequest = new PassportGetRequest();
            getRequest.setId(passport.getId());
            PassportGetResponse getResponse = passportManager.get(getRequest);
            if(getResponse.getPassport() == null){
                response.setResult(0L);
                return response;
            }
            PassportUpdateRequest updateRequest = this.getMapper().map(passport,PassportUpdateRequest.class);
            updateRequest.setRevokeTime(new Date());
            updateRequest.setRevokeType("LOGOUT");
            PassportUpdateResponse updateResponse = passportManager.update(updateRequest,passport);

            response.setResult(updateResponse.getResult());
        }else{
            response.setResult(0L);
        }
        return response;
    }
}
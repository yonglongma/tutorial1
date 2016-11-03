package com.alibaba.tutorial1.modules.system.biz;

import com.alibaba.tutorial1.modules.system.domain.Passport;
import com.alibaba.tutorial1.modules.system.request.PassportCreateRequest;
import com.alibaba.tutorial1.modules.system.request.PassportGetRequest;
import com.alibaba.tutorial1.modules.system.request.PassportUpdateRequest;
import com.alibaba.tutorial1.modules.system.response.PassportCreateResponse;
import com.alibaba.tutorial1.modules.system.response.PassportGetResponse;
import com.alibaba.tutorial1.modules.system.response.PassportUpdateResponse;

/**
 * Created by 马永龙 on 2016/9/22.
 */
public interface PassportManager {

    /**
     * 根据Id获取用户护照
     *
     * @param request 获取用户护照请求
     * @return 获取用户护照应答
     */
    PassportGetResponse get(PassportGetRequest request);

    /**
     * 创建用户护照
     *
     * @param request  创建用户护照请求
     * @param passport 用户护照
     * @return 创建用户护照应答
     */
    PassportCreateResponse create(PassportCreateRequest request, Passport passport);

    /**
     * 更新用户护照
     *
     * @param request  更新用户护照请求
     * @param passport 用户护照
     * @return 更新用户护照应答
     */
    PassportUpdateResponse update(PassportUpdateRequest request, Passport passport);

    /**
     * 批量注销用户护照
     *退出登录或修改密码需注销其他浏览器端的护照
     * @param passport 用户护照
     * @return 更新用户护照应答
     */
    PassportUpdateResponse invokeBatch(Passport passport);
}

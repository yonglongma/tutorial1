/**
 * @(#)UserPasswordMapper.java Copyright (c) 2014-2014  苏州犀牛网络科技有限公司 版权所有
 * xiniunet. All rights reserved.
 * <p/>
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
import com.alibaba.tutorial1.modules.system.po.UserPasswordPO;
import org.apache.ibatis.annotations.Param;

/**
 * Created by 马永龙 on 2016/10/3.
 */
@MyBatisRepository("AnotherUserPasswordMapper")
public interface UserPasswordMapper extends UserPasswordMapperAuto {

    /**
     * 更新实体对象
     *
     * @param request 请求对象
     * @return 受影响的记录条数
     */
    long update(@Param("request") UserPasswordPO request, @Param("passport") Passport passport);
}

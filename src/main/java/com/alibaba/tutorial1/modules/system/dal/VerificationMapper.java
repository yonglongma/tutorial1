/**
 * @(#)VerificationMapper.java Copyright (c) 2014-2014  苏州犀牛网络科技有限公司 版权所有
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
import com.alibaba.tutorial1.modules.system.po.VerificationPO;
import com.alibaba.tutorial1.modules.system.request.VerificationFindRequest;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by 马永龙 on 2016/10/3.
 */
@MyBatisRepository("AnotherVerificationMapper")
public interface VerificationMapper extends VerificationMapperAuto {

    /**
     * 更新实体对象
     *
     * @param request 请求对象
     * @return 受影响的记录条数
     */
    long update(@Param("request") VerificationPO request, @Param("passport") Passport passport);


    /**
     * 高级查询对象列表
     *
     * @param request  请求对象
     * @param passport 用户护照
     * @return 实体对象列表集合
     */
    List<VerificationPO> find(@Param("request") VerificationFindRequest request, @Param("passport") Passport passport);

    /**
     * 高级查询对象列表总数
     *
     * @param request  请求对象
     * @param passport 用户护照
     * @return 实体对象列表集合总数
     */
    long findCount(@Param("request") VerificationFindRequest request, @Param("passport") Passport passport);


}

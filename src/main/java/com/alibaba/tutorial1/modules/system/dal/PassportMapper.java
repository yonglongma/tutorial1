/**
 * @(#)PassportMapper.java Copyright (c) 2014-2014  苏州犀牛网络科技有限公司 版权所有
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

import com.alibaba.tutorial1.modules.system.domain.Passport;
import com.alibaba.tutorial1.modules.system.po.PassportPO;
import com.alibaba.tutorial1.modules.system.request.PassportFindRequest;
import com.alibaba.framework.annotation.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 马永龙 on 2016/10/3.
 */
@MyBatisRepository("PassportMapper")
public interface PassportMapper extends PassportMapperAuto {

    /**
     * 更新实体对象
     *
     * @param request 请求对象
     * @return 受影响的记录条数
     */
    long update(@Param("request") PassportPO request, @Param("passport") Passport passport);

    /**
     * 高级查询对象列表
     *
     * @param request 请求对象
     * @param passport 用户护照
     * @return 实体对象列表集合
     */
    List<PassportPO> find(@Param("request") PassportFindRequest request, @Param("passport") Passport passport);

    /**
     * 按主键ID批量注销护照
     *
     * @param list ID集合
     */
    long invokeBatch(@Param("list") List<Long> list, @Param("passport") Passport passport);
}

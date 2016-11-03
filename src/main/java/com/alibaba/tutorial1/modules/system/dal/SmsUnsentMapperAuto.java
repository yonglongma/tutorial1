/**
 * @(#)SmsUnsentMapperAuto.java
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
package com.alibaba.tutorial1.modules.system.dal;

import com.alibaba.tutorial1.modules.system.domain.Passport;
import com.alibaba.tutorial1.modules.system.po.SmsUnsentPO;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by 马永龙 on 2016/10/3.
 */
public interface SmsUnsentMapperAuto {
    /**
     * 插入记录.
     *
     * @param smsUnsent  实体对象
     * @return 实体对象的ID
     */
    long insert(@Param("smsUnsent") SmsUnsentPO smsUnsent, @Param("passport") Passport passport);

    /**
     * 批量插入记录.
     *
     * @param list  实体对象集合
     * @return 受影响的记录条数
     */
    long insertBatch(@Param("list") List<SmsUnsentPO> list, @Param("passport") Passport passport);

    /**
     * 按主键ID删除记录
     *
     * @param id 主键
     * @return 受影响的记录条数
     */
    long delete(@Param("id") Long id, @Param("passport") Passport passport);

    /**
     * 按主键ID批量删除记录
     *
     * @param list ID集合
     */
    long deleteBatch(@Param("list") List<Long> list, @Param("passport") Passport passport);

}

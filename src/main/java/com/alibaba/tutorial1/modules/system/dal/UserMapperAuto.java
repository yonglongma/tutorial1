package com.alibaba.tutorial1.modules.system.dal;

import com.alibaba.tutorial1.modules.system.domain.Passport;
import com.alibaba.tutorial1.modules.system.domain.User;
import com.alibaba.tutorial1.modules.system.po.UserPO;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by 马永龙 on 2016/9/10.
 */
public interface UserMapperAuto {
    /**
     * 插入记录.
     *
     * @param user 实体对象
     * @return 实体对象的ID
     */
    long insert(@Param("user") UserPO user, @Param("passport") Passport passport);

    /**
     * 批量插入记录.
     *
     * @param list 实体对象集合
     * @return 受影响的记录条数
     */
    long insertBatch(@Param("list") List<UserPO> list, @Param("passport") Passport passport);

    /**
     * 通过ID查询对象
     */
    User get(@Param("id")Long id, @Param("passport")Passport passport);
}

/**
 * @(#)UserStatisticsFindRequest.java
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
package com.alibaba.tutorial1.modules.system.request;

import com.alibaba.framework.base.BaseFindRequest;

/**
 * Created by 马永龙 on 2016/10/3.
 */
public class UserStatisticsFindRequest extends BaseFindRequest {

    
    
    /**
     * 用户ID,
     */
    private  Long   id;

    /**
     * 评论数,
     */
    private  Integer   postCount;

    /**
     * 点赞数,
     */
    private  Integer   likeCount;

    /**
     * 收藏数,
     */
    private  Integer   collectionCount;

    /**
     * 未读消息数量
     */
    private Integer unreadMessageCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPostCount() {
        return postCount;
    }

    public void setPostCount(Integer postCount) {
        this.postCount = postCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getCollectionCount() {
        return collectionCount;
    }

    public void setCollectionCount(Integer collectionCount) {
        this.collectionCount = collectionCount;
    }

    public Integer getUnreadMessageCount() {
        return unreadMessageCount;
    }

    public void setUnreadMessageCount(Integer unreadMessageCount) {
        this.unreadMessageCount = unreadMessageCount;
    }
}

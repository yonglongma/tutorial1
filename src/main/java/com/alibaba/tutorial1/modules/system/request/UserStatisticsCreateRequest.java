package com.alibaba.tutorial1.modules.system.request;

import com.alibaba.framework.base.BaseRequest;

/**
 * Created by 马永龙 on 2016/10/3.
 */
public class UserStatisticsCreateRequest extends BaseRequest{

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 评论数
     */
    private Integer postCount;

    /**
     * 点赞数
     */
    private Integer likeCount;

    /**
     * 收藏数
     */
    private Integer collectionCount;

    /**
     * 未读消息数量
     */
    private Integer unreadMessageCount;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

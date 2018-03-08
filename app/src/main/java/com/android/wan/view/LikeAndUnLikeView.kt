package com.android.wan.view

import com.android.wan.net.response.HomeListResponse

/**
 * @author by 有人@我 on 18/3/8.
 */
interface LikeAndUnLikeView : BaseView {
    /**
     * 绑定收藏文章
     */
    fun bindLikeAction(homeListResponse: HomeListResponse)

    /**
     * 绑定取消收藏
     */
    fun bindUnLikeAction(homeListResponse: HomeListResponse)
}
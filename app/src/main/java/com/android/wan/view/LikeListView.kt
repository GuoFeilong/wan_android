package com.android.wan.view

import com.android.wan.net.response.HomeListResponse

/**
 * @author by 有人@我 on 18/3/8.
 */
interface LikeListView : BaseView {
    /**
     * 绑定我的收藏列表数据
     */
    fun bindMyLikeList(homeListResponse: HomeListResponse)
}
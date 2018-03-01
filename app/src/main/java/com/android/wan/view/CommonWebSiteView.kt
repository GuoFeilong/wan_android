package com.android.wan.view

import com.android.wan.net.response.FriendListResponse

/**
 * @author by 有人@我 on 18/3/1.
 */
interface CommonWebSiteView : BaseView {
    /**
     * 绑定常用网站数据
     */
    fun bindCommonWebSite(friendListResponse: FriendListResponse)
}

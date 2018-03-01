package com.android.wan.view

import com.android.wan.net.response.HotKeyResponse

/**
 * @author by 有人@我 on 18/3/1.
 */
interface HotSearchView : BaseView {
    /**
     * 绑定热搜数据
     */
    fun bindHotSearchTitle(hotKeyResponse: HotKeyResponse)
}
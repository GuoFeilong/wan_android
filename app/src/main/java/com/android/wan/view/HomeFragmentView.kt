package com.android.wan.view

import com.android.wan.net.response.BannerResponse

/**
 * @author by 有人@我 on 2018/1/13.
 */
interface HomeFragmentView : BaseView {
    /**
     * 绑定轮播数据
     */
    fun bindBannerData(bannerResponse: BannerResponse)
}
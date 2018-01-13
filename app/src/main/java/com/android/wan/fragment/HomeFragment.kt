package com.android.wan.fragment

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.wan.R
import com.android.wan.base.AbstractFragment
import com.android.wan.net.response.BannerResponse
import com.android.wan.presenter.HomeFragmentPresenter
import com.android.wan.view.HomeFragmentView
import com.orhanobut.logger.Logger

/**
 * @author by 有人@我 on 2018/1/12.
 */
class HomeFragment : AbstractFragment(), HomeFragmentView {
    override fun showLoading() {
        Toast.makeText(activityContext, "显示loading", Toast.LENGTH_SHORT).show()
    }

    override fun bindBannerData(bannerResponse: BannerResponse) {
        Toast.makeText(activityContext, bannerResponse.toString(), Toast.LENGTH_SHORT).show()
        Log.e("---轮播结果", bannerResponse.toString())
    }

    override fun hideLoading() {
        Toast.makeText(activityContext, "隐藏loading", Toast.LENGTH_SHORT).show()
    }

    override fun showToast(msg: String) {
        Toast.makeText(activityContext, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showErr() {
        Toast.makeText(activityContext, "请求错误", Toast.LENGTH_SHORT).show()
    }

    override fun getContext(): Context {
        return activityContext!!
    }

    var homeFragmentPresenter: HomeFragmentPresenter? = null

    override fun setFragmentLayout(): Int {
        return R.layout.fragment_home
    }

    override fun initData() {
        homeFragmentPresenter = HomeFragmentPresenter()
        homeFragmentPresenter!!.attachView(this)
    }

    override fun initEvent() {
        homeFragmentPresenter!!.getBannerData()
    }

}
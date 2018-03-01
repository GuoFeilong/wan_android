package com.android.wan.presenter

import android.content.Context
import com.android.wan.callback.MvpCallback
import com.android.wan.moudle.CommonWebSiteMoudle
import com.android.wan.net.response.FriendListResponse
import com.android.wan.view.CommonWebSiteView

/**
 * @author by 有人@我 on 18/3/1.
 */
class CommonWebSitePresenter : BasePresenter<CommonWebSiteView>() {
    var commonWebSiteView: CommonWebSiteView? = null
    var commonWebSiteMoudle: CommonWebSiteMoudle? = CommonWebSiteMoudle()
    var activityContex: Context? = null

    override fun attachView(mvpView: CommonWebSiteView) {
        this.commonWebSiteView = mvpView
        activityContex = mvpView.getContext()
    }

    override fun detachView() {
        this.commonWebSiteView = null
    }

    override fun isViewAttached(): Boolean {
        return this.commonWebSiteView !== null
    }

    override fun getView(): CommonWebSiteView {
        return this.commonWebSiteView!!
    }

    fun getCommonWebSite() {
        commonWebSiteView?.showLoading()
        commonWebSiteMoudle?.getCommonWebSite(object : MvpCallback<FriendListResponse> {
            override fun onSuccess(data: FriendListResponse) {
                commonWebSiteView!!.bindCommonWebSite(data)
            }

            override fun onFailure(msg: String) {
                commonWebSiteView!!.showToast(msg)
            }

            override fun onError() {
                commonWebSiteView!!.showErr()
            }

            override fun onComplete() {
                commonWebSiteView!!.hideLoading()
            }

            override fun showLoading() {
                commonWebSiteView!!.showLoading()
            }

            override fun dissMissLoading() {
                commonWebSiteView!!.hideLoading()
            }
        })
    }

}
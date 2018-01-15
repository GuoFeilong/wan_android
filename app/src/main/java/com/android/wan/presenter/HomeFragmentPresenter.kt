package com.android.wan.presenter

import android.content.Context
import com.android.wan.callback.MvpCallback
import com.android.wan.moudle.HomeFragmentMoudle
import com.android.wan.net.response.BannerResponse
import com.android.wan.net.response.HomeListResponse
import com.android.wan.view.HomeFragmentView

/**
 * @author by 有人@我 on 2018/1/13.
 */
class HomeFragmentPresenter : BasePresenter<HomeFragmentView>() {

    var homeFragmentMoudle: HomeFragmentMoudle = HomeFragmentMoudle()
    var homeFragmentView: HomeFragmentView? = null
    var activityContex: Context? = null

    override fun attachView(mvpView: HomeFragmentView) {
        this.homeFragmentView = mvpView
        activityContex = mvpView.getContext()
    }

    override fun detachView() {
        this.homeFragmentView = null
    }

    override fun isViewAttached(): Boolean {
        return this.homeFragmentView !== null
    }

    override fun getView(): HomeFragmentView {
        return this.homeFragmentView!!
    }

    fun getBannerData() {
        homeFragmentView?.showLoading()
        homeFragmentMoudle.getBannerData(object : MvpCallback<BannerResponse> {
            override fun onSuccess(data: BannerResponse) {
                homeFragmentView!!.bindBannerData(data)
            }

            override fun onFailure(msg: String) {
                homeFragmentView!!.showToast(msg)
            }

            override fun onError() {
                homeFragmentView!!.showErr()
            }

            override fun onComplete() {
                homeFragmentView!!.hideLoading()
            }

            override fun showLoading() {
                homeFragmentView!!.showLoading()
            }

            override fun dissMissLoading() {
                homeFragmentView!!.hideLoading()
            }

        }
        )
    }

    fun getHomeListArticle(pageIndex: Int) {
        homeFragmentView?.showLoading()
        homeFragmentMoudle.getHomeListData(object : MvpCallback<HomeListResponse> {
            override fun onSuccess(data: HomeListResponse) {
                homeFragmentView?.bindHomeArticle(data)
            }

            override fun onFailure(msg: String) {
                homeFragmentView?.showToast(msg)
            }

            override fun onError() {
                homeFragmentView?.showErr()
            }

            override fun onComplete() {
                homeFragmentView?.hideLoading()
            }

            override fun showLoading() {
                homeFragmentView?.showLoading()
            }

            override fun dissMissLoading() {
                homeFragmentView?.hideLoading()
            }

        }, pageIndex)
    }

}
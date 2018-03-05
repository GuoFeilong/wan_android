package com.android.wan.presenter

import android.content.Context
import com.android.wan.callback.MvpCallback
import com.android.wan.moudle.HotSearchMoudle
import com.android.wan.net.response.HomeListResponse
import com.android.wan.net.response.HotKeyResponse
import com.android.wan.view.HotSearchView

/**
 * @author by 有人@我 on 18/3/1.
 */
class HotSearchPresenter : BasePresenter<HotSearchView>() {

    var hotSearchView: HotSearchView? = null
    var hotSearchMoudle: HotSearchMoudle? = HotSearchMoudle()
    var activityContex: Context? = null

    override fun attachView(mvpView: HotSearchView) {
        this.hotSearchView = mvpView
        activityContex = mvpView.getContext()
    }

    override fun detachView() {
        this.hotSearchView = null
    }

    override fun isViewAttached(): Boolean {
        return this.hotSearchView !== null
    }

    override fun getView(): HotSearchView {
        return this.hotSearchView!!
    }

    fun getHotSearchKeys() {
        hotSearchView?.showLoading()
        hotSearchMoudle?.getHotSearchKeys(object : MvpCallback<HotKeyResponse> {
            override fun onSuccess(data: HotKeyResponse) {
                hotSearchView!!.bindHotSearchTitle(data)
            }

            override fun onFailure(msg: String) {
                hotSearchView!!.showToast(msg)
            }

            override fun onError() {
                hotSearchView!!.showErr()
            }

            override fun onComplete() {
                hotSearchView!!.hideLoading()
            }

            override fun showLoading() {
                hotSearchView!!.showLoading()
            }

            override fun dissMissLoading() {
                hotSearchView!!.hideLoading()
            }
        })
    }

    fun getHotSearchResult(page: Int, searchKey: String) {
        hotSearchView?.showLoading()
        hotSearchMoudle?.getHotSearchResult(page, searchKey, object : MvpCallback<HomeListResponse> {
            override fun onSuccess(data: HomeListResponse) {
                hotSearchView!!.bindHotSearchResult(data)
            }

            override fun onFailure(msg: String) {
                hotSearchView!!.showToast(msg)
            }

            override fun onError() {
                hotSearchView!!.showErr()
            }

            override fun onComplete() {
                hotSearchView!!.hideLoading()
            }

            override fun showLoading() {
                hotSearchView!!.showLoading()
            }

            override fun dissMissLoading() {
                hotSearchView!!.hideLoading()
            }
        })
    }
}
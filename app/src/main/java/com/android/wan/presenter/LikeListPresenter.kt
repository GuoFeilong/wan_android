package com.android.wan.presenter

import android.content.Context
import com.android.wan.callback.MvpCallback
import com.android.wan.moudle.LikeListMoudle
import com.android.wan.net.response.HomeListResponse
import com.android.wan.view.LikeListView

/**
 * @author by 有人@我 on 18/3/8.
 */
class LikeListPresenter : BasePresenter<LikeListView>() {
    var activityContext: Context? = null
    var likeListView: LikeListView? = null
    var likeListMoudle: LikeListMoudle = LikeListMoudle()

    override fun attachView(mvpView: LikeListView) {
        this.likeListView = mvpView
        activityContext = mvpView.getContext()
    }

    override fun detachView() {
        this.likeListView = null
    }

    override fun isViewAttached(): Boolean {
        return this.likeListView !== null
    }

    override fun getView(): LikeListView {
        return this.likeListView!!
    }


    fun getLikeList(page: Int) {
        likeListView?.showLoading()
        likeListMoudle.getLikeList(page, object : MvpCallback<HomeListResponse> {
            override fun onSuccess(data: HomeListResponse) {
                likeListView?.bindMyLikeList(data)
            }

            override fun onFailure(msg: String) {
                likeListView?.showToast(msg)
            }

            override fun onError() {
                likeListView?.showErr()
            }

            override fun onComplete() {
                likeListView?.hideLoading()
            }

            override fun showLoading() {
                likeListView?.showLoading()
            }

            override fun dissMissLoading() {
                likeListView?.hideLoading()
            }

        })
    }


}
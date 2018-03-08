package com.android.wan.presenter

import android.content.Context
import com.android.wan.callback.MvpCallback
import com.android.wan.moudle.LikeAndUnLikeMoudle
import com.android.wan.net.response.HomeListResponse
import com.android.wan.view.LikeAndUnLikeView

/**
 * @author by 有人@我 on 18/3/8.
 */
class LikeAndUnLikePresenter : BasePresenter<LikeAndUnLikeView>() {
    var activityContext: Context? = null
    var likeAndUnLikeView: LikeAndUnLikeView? = null
    var likeAndUnLikeMoudle: LikeAndUnLikeMoudle = LikeAndUnLikeMoudle()

    override fun attachView(mvpView: LikeAndUnLikeView) {
        this.likeAndUnLikeView = mvpView
        activityContext = mvpView.getContext()
    }

    override fun detachView() {
        this.likeAndUnLikeView = null
    }

    override fun isViewAttached(): Boolean {
        return this.likeAndUnLikeView !== null
    }

    override fun getView(): LikeAndUnLikeView {
        return likeAndUnLikeView!!
    }

    fun likeAction(id: Int) {
        likeAndUnLikeView?.showLoading()
        likeAndUnLikeMoudle.likeAction(id, object : MvpCallback<HomeListResponse> {
            override fun onSuccess(data: HomeListResponse) {
                likeAndUnLikeView?.bindLikeAction(data)
            }

            override fun onFailure(msg: String) {
                likeAndUnLikeView?.showToast(msg)
            }

            override fun onError() {
                likeAndUnLikeView?.showErr()
            }

            override fun onComplete() {
                likeAndUnLikeView?.hideLoading()
            }

            override fun showLoading() {
                likeAndUnLikeView?.showLoading()
            }

            override fun dissMissLoading() {
                likeAndUnLikeView?.hideLoading()
            }

        })
    }


    fun unLikeAction(id: Int, originId: Int) {
        likeAndUnLikeView?.showLoading()
        likeAndUnLikeMoudle.unLikeAction(id, originId, object : MvpCallback<HomeListResponse> {
            override fun onSuccess(data: HomeListResponse) {
                likeAndUnLikeView?.bindUnLikeAction(data)
            }

            override fun onFailure(msg: String) {
                likeAndUnLikeView?.showToast(msg)
            }

            override fun onError() {
                likeAndUnLikeView?.showErr()
            }

            override fun onComplete() {
                likeAndUnLikeView?.hideLoading()
            }

            override fun showLoading() {
                likeAndUnLikeView?.showLoading()
            }

            override fun dissMissLoading() {
                likeAndUnLikeView?.hideLoading()
            }

        })
    }
}
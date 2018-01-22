package com.android.wan.presenter

import android.content.Context
import com.android.wan.callback.MvpCallback
import com.android.wan.moudle.ArticleListMoudle
import com.android.wan.net.response.ArticleListResponse
import com.android.wan.view.ArticleListView

/**
 * @author by 有人@我 on 18/1/22.
 */
class ArticleListPresenter : BasePresenter<ArticleListView>() {
    var articleListMoudle: ArticleListMoudle = ArticleListMoudle()
    var articleListView: ArticleListView? = null
    var activityContex: Context? = null


    override fun attachView(mvpView: ArticleListView) {
        this.articleListView = mvpView
        activityContex = mvpView.getContext()
    }

    override fun detachView() {
        this.articleListView = null
    }

    override fun isViewAttached(): Boolean {
        return this.articleListView !== null
    }

    override fun getView(): ArticleListView {
        return this.articleListView!!
    }


    fun getArticleListData(page: Int, cid: Int) {
        articleListView?.showLoading()
        articleListMoudle.getArticleList(page, cid, object : MvpCallback<ArticleListResponse> {
            override fun onSuccess(data: ArticleListResponse) {
                articleListView!!.bindArticleList(data)
            }

            override fun onFailure(msg: String) {
                articleListView!!.showToast(msg)
            }

            override fun onError() {
                articleListView!!.showErr()
            }

            override fun onComplete() {
                articleListView!!.hideLoading()
            }

            override fun showLoading() {
                articleListView!!.showLoading()
            }

            override fun dissMissLoading() {
                articleListView!!.hideLoading()
            }

        })
    }
}
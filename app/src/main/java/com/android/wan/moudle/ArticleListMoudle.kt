package com.android.wan.moudle

import RetrofitHelper
import com.android.wan.callback.MvpCallback
import com.android.wan.net.response.ArticleListResponse
import rx.Subscriber
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * @author by 有人@我 on 18/1/22.
 */
class ArticleListMoudle {

    fun getArticleList(page: Int, cid: Int, mvpCallback: MvpCallback<ArticleListResponse>) {
        var subscription: Subscription? = null
        subscription?.unsubscribe()
        subscription = RetrofitHelper.retrofitService.getArticleList(page, cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    mvpCallback.showLoading()
                }
                .subscribe(object : Subscriber<ArticleListResponse>() {
                    override fun onCompleted() {
                        subscription?.unsubscribe()
                        mvpCallback.dissMissLoading()
                    }

                    override fun onError(e: Throwable?) {
                        mvpCallback.onFailure(e.toString())
                    }

                    override fun onNext(t: ArticleListResponse?) {
                        mvpCallback.onSuccess(t!!)
                    }
                })
    }
}
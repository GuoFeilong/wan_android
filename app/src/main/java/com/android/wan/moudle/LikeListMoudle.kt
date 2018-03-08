package com.android.wan.moudle

import com.android.wan.callback.MvpCallback
import com.android.wan.net.response.HomeListResponse
import rx.Subscriber
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * @author by 有人@我 on 18/3/8.
 */
class LikeListMoudle {

    fun getLikeList(page: Int, mvpCallback: MvpCallback<HomeListResponse>) {
        var subscription: Subscription? = null
        subscription?.unsubscribe()
        subscription = RetrofitHelper
                .retrofitService
                .getLikeList(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    mvpCallback.showLoading()
                }
                .subscribe(object : Subscriber<HomeListResponse>() {
                    override fun onError(e: Throwable?) {
                        mvpCallback.onError()
                    }

                    override fun onCompleted() {
                        subscription?.unsubscribe()
                        mvpCallback.dissMissLoading()
                    }

                    override fun onNext(t: HomeListResponse?) {
                        mvpCallback.onSuccess(t!!)
                    }
                })
    }

}
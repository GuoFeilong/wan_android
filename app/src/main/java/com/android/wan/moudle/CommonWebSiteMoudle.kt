package com.android.wan.moudle

import RetrofitHelper
import com.android.wan.callback.MvpCallback
import com.android.wan.net.response.FriendListResponse
import rx.Subscriber
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * @author by 有人@我 on 18/3/1.
 */
class CommonWebSiteMoudle {

    fun getCommonWebSite(mvpCallback: MvpCallback<FriendListResponse>) {
        var subscription: Subscription? = null
        subscription?.unsubscribe()
        subscription = RetrofitHelper.retrofitService.getFriendList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    mvpCallback.showLoading()
                }
                .subscribe(object : Subscriber<FriendListResponse>() {
                    override fun onCompleted() {
                        subscription?.unsubscribe()
                        mvpCallback.dissMissLoading()
                    }

                    override fun onError(e: Throwable?) {
                        mvpCallback.onFailure(e.toString())
                    }

                    override fun onNext(t: FriendListResponse?) {
                        mvpCallback.onSuccess(t!!)
                    }
                })
    }
}
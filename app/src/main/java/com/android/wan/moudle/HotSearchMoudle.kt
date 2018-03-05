package com.android.wan.moudle

import RetrofitHelper
import com.android.wan.callback.MvpCallback
import com.android.wan.net.response.HomeListResponse
import com.android.wan.net.response.HotKeyResponse
import rx.Subscriber
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * @author by 有人@我 on 18/3/1.
 */
class HotSearchMoudle {
    fun getHotSearchKeys(mvpCallback: MvpCallback<HotKeyResponse>) {
        var subscription: Subscription? = null
        subscription?.unsubscribe()
        subscription = RetrofitHelper.retrofitService.getHotKeyList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    mvpCallback.showLoading()
                }
                .subscribe(object : Subscriber<HotKeyResponse>() {
                    override fun onCompleted() {
                        subscription?.unsubscribe()
                        mvpCallback.dissMissLoading()
                    }

                    override fun onError(e: Throwable?) {
                        mvpCallback.onFailure(e.toString())
                    }

                    override fun onNext(t: HotKeyResponse?) {
                        mvpCallback.onSuccess(t!!)
                    }
                })

    }

    fun getHotSearchResult(page: Int, searchKey: String, mvpCallback: MvpCallback<HomeListResponse>) {
        var subscription: Subscription? = null
        subscription?.unsubscribe()
        subscription = RetrofitHelper.retrofitService.getSearchList(page, searchKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    mvpCallback.showLoading()
                }
                .subscribe(object : Subscriber<HomeListResponse>() {
                    override fun onCompleted() {
                        subscription?.unsubscribe()
                        mvpCallback.dissMissLoading()
                    }

                    override fun onError(e: Throwable?) {
                        mvpCallback.onFailure(e.toString())
                    }

                    override fun onNext(t: HomeListResponse?) {
                        mvpCallback.onSuccess(t!!)
                    }
                })
    }
}
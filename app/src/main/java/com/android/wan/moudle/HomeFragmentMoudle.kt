package com.android.wan.moudle

import RetrofitHelper
import android.util.Log
import com.android.wan.callback.MvpCallback
import com.android.wan.net.response.BannerResponse
import rx.Subscriber
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * @author by Jsion on 2018/1/13.
 */

class HomeFragmentMoudle() {
    var subscription: Subscription? = null

    fun getBannerData(mvpCallback: MvpCallback<BannerResponse>) {
        subscription?.unsubscribe()
        subscription = RetrofitHelper.retrofitService.getBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    mvpCallback.showLoading()
                }
                .subscribe(object : Subscriber<BannerResponse>() {
                    override fun onCompleted() {
                        mvpCallback.dissMissLoading()
                    }

                    override fun onError(e: Throwable?) {
                        mvpCallback.onFailure(e.toString())
                    }

                    override fun onNext(t: BannerResponse?) {
                        mvpCallback.onSuccess(t!!)
                    }
                })
    }
}


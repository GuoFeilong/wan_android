package com.android.wan.moudle

import RetrofitHelper
import com.android.wan.callback.MvpCallback
import com.android.wan.net.response.BannerResponse
import com.android.wan.net.response.HomeListResponse
import rx.Subscriber
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * @author by 有人@我 on 2018/1/13.
 */

class HomeFragmentMoudle {

    fun getBannerData(mvpCallback: MvpCallback<BannerResponse>) {
        var subscription: Subscription? = null
        subscription?.unsubscribe()
        subscription = RetrofitHelper.retrofitService.getBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    mvpCallback.showLoading()
                }
                .subscribe(object : Subscriber<BannerResponse>() {
                    override fun onCompleted() {
                        subscription?.unsubscribe()
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

    fun getHomeListData(mvpCallback: MvpCallback<HomeListResponse>, pageIndex: Int) {
        var subscription: Subscription? = null
        subscription?.unsubscribe()
        subscription = RetrofitHelper.retrofitService.getHomeList(pageIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    mvpCallback.showLoading()
                }
                .subscribe(object : Subscriber<HomeListResponse>() {
                    override fun onError(e: Throwable?) {
                        mvpCallback.onFailure(e.toString())
                    }

                    override fun onNext(t: HomeListResponse?) {
                        mvpCallback.onSuccess(t!!)
                    }

                    override fun onCompleted() {
                        subscription?.unsubscribe()
                        mvpCallback.dissMissLoading()
                    }

                })
    }

}


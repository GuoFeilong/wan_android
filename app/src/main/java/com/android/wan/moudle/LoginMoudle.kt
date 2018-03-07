package com.android.wan.moudle

import com.android.wan.callback.MvpCallback
import com.android.wan.net.response.LoginResponse
import rx.Subscriber
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * @author by 有人@我 on 18/3/7.
 */
class LoginMoudle {

    fun loginAccount(userName: String, passWord: String, mvpCallback: MvpCallback<LoginResponse>) {
        var subscription: Subscription? = null
        subscription?.unsubscribe()
        subscription = RetrofitHelper.retrofitService.loginWanAndroid(
                userName,
                passWord
        ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    mvpCallback.showLoading()
                }
                .subscribe(object : Subscriber<LoginResponse>() {
                    override fun onCompleted() {
                        subscription?.unsubscribe()
                        mvpCallback.dissMissLoading()
                    }

                    override fun onError(e: Throwable?) {
                        mvpCallback.onFailure(e.toString())
                    }

                    override fun onNext(t: LoginResponse?) {
                        mvpCallback.onSuccess(t!!)
                    }
                })
    }

}
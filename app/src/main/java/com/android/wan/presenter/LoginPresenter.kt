package com.android.wan.presenter

import android.content.Context
import com.android.wan.callback.MvpCallback
import com.android.wan.moudle.LoginMoudle
import com.android.wan.net.response.LoginResponse
import com.android.wan.view.LoginView

/**
 * @author by 有人@我 on 18/3/7.
 */
class LoginPresenter : BasePresenter<LoginView>() {
    var loginView: LoginView? = null
    var loginMoudle: LoginMoudle = LoginMoudle()
    var activityContext: Context? = null

    override fun attachView(mvpView: LoginView) {
        this.loginView = mvpView
        activityContext = mvpView.getContext()
    }

    override fun detachView() {
        loginView = null
    }

    override fun isViewAttached(): Boolean {
        return loginView !== null
    }

    override fun getView(): LoginView {
        return this.loginView!!
    }

    fun loginAccount(userName: String, passWord: String) {
        loginView?.showLoading()
        loginMoudle.loginAccount(userName, passWord, object : MvpCallback<LoginResponse> {
            override fun onSuccess(data: LoginResponse) {
                loginView?.bindLoginData(data)
            }

            override fun onFailure(msg: String) {
                loginView?.showToast(msg)
            }

            override fun onError() {
                loginView?.showErr()
            }

            override fun onComplete() {
                loginView?.hideLoading()
            }

            override fun showLoading() {
                loginView?.showLoading()
            }

            override fun dissMissLoading() {
                loginView?.hideLoading()
            }

        })
    }

}
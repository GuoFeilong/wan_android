package com.android.wan.presenter

import android.content.Context
import com.android.wan.callback.MvpCallback
import com.android.wan.moudle.SignUpMoudle
import com.android.wan.net.response.LoginResponse
import com.android.wan.view.SignUpView

/**
 * @author by 有人@我 on 18/3/7.
 */
class SignUpPresenter : BasePresenter<SignUpView>() {
    var signUpView: SignUpView? = null
    var signUpMoudle: SignUpMoudle = SignUpMoudle()
    var activityContext: Context? = null

    override fun attachView(mvpView: SignUpView) {
        this.signUpView = mvpView
        activityContext = mvpView.getContext()
    }

    override fun detachView() {
        signUpView = null
    }

    override fun isViewAttached(): Boolean {
        return signUpView !== null
    }

    override fun getView(): SignUpView {
        return this.signUpView!!
    }

    fun signUpAccount(userName: String, passWord: String, rePassWord: String) {
        signUpView?.showLoading()
        signUpMoudle.signUpAccount(userName, passWord, rePassWord, object : MvpCallback<LoginResponse> {
            override fun onSuccess(data: LoginResponse) {
                signUpView?.bindSignUpData(data)
            }

            override fun onFailure(msg: String) {
                signUpView?.showToast(msg)
            }

            override fun onError() {
                signUpView?.showErr()
            }

            override fun onComplete() {
                signUpView?.hideLoading()
            }

            override fun showLoading() {
                signUpView?.showLoading()
            }

            override fun dissMissLoading() {
                signUpView?.hideLoading()
            }

        })
    }

}
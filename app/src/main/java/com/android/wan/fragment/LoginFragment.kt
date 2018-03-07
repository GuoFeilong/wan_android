package com.android.wan.fragment

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.android.wan.R
import com.android.wan.base.AbstractFragment
import com.android.wan.customwidget.WithdrawClearEditText
import com.android.wan.net.response.LoginResponse
import com.android.wan.presenter.LoginPresenter
import com.android.wan.view.LoginView

/**
 * @author by 有人@我 on 18/3/6.
 */
class LoginFragment : AbstractFragment(), LoginView {

    var loginPresenter: LoginPresenter? = null
    var account: WithdrawClearEditText? = null
    var password: WithdrawClearEditText? = null
    var login: TextView? = null

    override fun setFragmentLayout(): Int {
        return R.layout.fragment_login
    }

    override fun initData() {
        loginPresenter = LoginPresenter()
        loginPresenter?.attachView(this)
    }

    override fun initEvent() {
        login?.setOnClickListener {
            if (localCheck()) {
                loginPresenter?.loginAccount(account?.text.toString(), password?.text.toString())
            }
        }
    }

    override fun initView(rootView: View) {
        account = rootView.findViewById(R.id.wce_account)
        password = rootView.findViewById(R.id.wce_pass_word)
        login = rootView.findViewById(R.id.tv_login)
    }

    override fun showLoading() {

    }

    override fun bindLoginData(loginResponse: LoginResponse) {
        when (loginResponse.errorCode) {
            0 -> {
                Log.e("---loginResponse--->", loginResponse.toString())
            }
            else -> {
                Toast.makeText(activityContext, loginResponse.errorMsg, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun hideLoading() {
    }

    override fun showToast(msg: String) {
        Toast.makeText(activityContext, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showErr() {
        Toast.makeText(activityContext, "请求错误", Toast.LENGTH_SHORT).show()
    }

    override fun getContext(): Context {
        return activityContext!!
    }

    fun localCheck(): Boolean {
        if (TextUtils.isEmpty(account?.text)) {
            Toast.makeText(activityContext, "账号不能为空", Toast.LENGTH_SHORT).show()
            return false
        }
        if (TextUtils.isEmpty(password?.text)) {
            Toast.makeText(activityContext, "密码不能为空", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

}
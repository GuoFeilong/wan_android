package com.android.wan.fragment

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.android.wan.R
import com.android.wan.base.AbstractFragment
import com.android.wan.callback.OnSignUpSuccessListener
import com.android.wan.customwidget.WithdrawClearEditText
import com.android.wan.net.response.LoginResponse
import com.android.wan.presenter.SignUpPresenter
import com.android.wan.view.SignUpView

/**
 * @author by 有人@我 on 18/3/6.
 */
class SignUpFragment : AbstractFragment(), SignUpView {
    var account: WithdrawClearEditText? = null
    var password: WithdrawClearEditText? = null
    var rePassword: WithdrawClearEditText? = null
    var signUp: TextView? = null
    var signUpPresenter: SignUpPresenter? = null
    var accountText: String = ""
    var passwordText: String = ""
    var rePasswordText: String = ""
    var onSignUpSuccessListener: OnSignUpSuccessListener? = null


    override fun setFragmentLayout(): Int {
        return R.layout.fragment_sign_up
    }

    override fun initData() {
        signUpPresenter = SignUpPresenter()
        signUpPresenter?.attachView(this)
    }

    override fun initEvent() {
        signUp?.setOnClickListener {
            if (localCheck()) {
                accountText = account!!.text.toString()
                passwordText = password!!.text.toString()
                rePasswordText = rePassword!!.text.toString()
                signUpPresenter?.signUpAccount(accountText, passwordText, rePasswordText)
            }
        }
    }

    override fun initView(rootView: View) {
        account = rootView.findViewById(R.id.wce_account)
        password = rootView.findViewById(R.id.wce_pass_word)
        rePassword = rootView.findViewById(R.id.wce_pass_word_confirm)
        signUp = rootView.findViewById(R.id.tv_sign_up)
    }

    override fun showLoading() {

    }

    override fun bindSignUpData(loginResponse: LoginResponse) {
        when (loginResponse.errorCode) {
            0 -> {
                onSignUpSuccessListener?.signUpSuccess()
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
        if (TextUtils.isEmpty(rePassword?.text)) {
            Toast.makeText(activityContext, "确认密码不能为空", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!(password!!.text.toString().equals(rePassword!!.text.toString()))) {
            Toast.makeText(activityContext, "两次密码不一致", Toast.LENGTH_SHORT).show()
            return false
        }
        if (password!!.text.length < 6) {
            Toast.makeText(activityContext, "密码长度最少为6位", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    fun setSignUpListener(onSignUpSuccessListener: OnSignUpSuccessListener) {
        this.onSignUpSuccessListener = onSignUpSuccessListener
    }
}
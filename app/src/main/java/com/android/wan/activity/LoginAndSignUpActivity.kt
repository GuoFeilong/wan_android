package com.android.wan.activity

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.android.wan.R
import com.android.wan.base.AbstractActivity
import com.android.wan.callback.OnLoginSuccessListener
import com.android.wan.callback.OnSignUpSuccessListener
import com.android.wan.constant.Constant
import com.android.wan.fragment.LoginFragment
import com.android.wan.fragment.SignUpFragment
import com.android.wan.net.response.LoginResponse
import com.android.wan.utils.SharedPreferencesUtil
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser

/**
 * @author by 有人@我 on 18/3/6.
 */
class LoginAndSignUpActivity : AbstractActivity(), View.OnClickListener {

    var signUp: TextView? = null
    var back: ImageView? = null
    var fragmentContainer: FrameLayout? = null
    var loginFrameLayout: LoginFragment? = null
    var signUpFragment: SignUpFragment? = null
    var showFragmentStatus: Int = Constant.SHOW_SIGNUP

    override fun initData() {
        loginFrameLayout = LoginFragment()
        signUpFragment = SignUpFragment()
    }

    override fun initEvent() {
        signUp?.setOnClickListener(this)
        back?.setOnClickListener(this)
        loginFrameLayout?.onLoginSuccessListener = object : OnLoginSuccessListener {
            override fun loginSuccess(loginResponse: LoginResponse) {
                SharedPreferencesUtil.saveShareData(this@LoginAndSignUpActivity, Gson().toJson(loginResponse), Constant.LOGIN_SUCCESS_KEY)
                setResult(Activity.RESULT_OK)
                finish()
            }

        }
        signUpFragment?.onSignUpSuccessListener = object : OnSignUpSuccessListener {
            override fun signUpSuccess() {
                supportFragmentManager.beginTransaction()
                        .hide(signUpFragment)
                        .show(loginFrameLayout)
                        .commitAllowingStateLoss()
                showFragmentStatus = Constant.SHOW_SIGNUP
                signUp?.setText("注册")
            }
        }

        supportFragmentManager.beginTransaction()
                .add(R.id.fl_fragment_container, loginFrameLayout)
                .add(R.id.fl_fragment_container, signUpFragment)
                .hide(signUpFragment)
                .commitAllowingStateLoss()
    }

    override fun setContentLayoutId(): Int {
        return R.layout.activity_login_and_signup
    }

    override fun initView() {
        signUp = findViewById(R.id.tv_sign_up)
        back = findViewById(R.id.iv_back)
        fragmentContainer = findViewById(R.id.fl_fragment_container)
        applyStatusBarColor(android.R.color.white)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_back -> {
                finish()
            }
            R.id.tv_sign_up -> {

                if (showFragmentStatus == Constant.SHOW_LOGIN) {
                    supportFragmentManager.beginTransaction()
                            .hide(signUpFragment)
                            .show(loginFrameLayout)
                            .commitAllowingStateLoss()
                    showFragmentStatus = Constant.SHOW_SIGNUP
                    signUp?.setText("注册")
                } else {
                    supportFragmentManager.beginTransaction()
                            .hide(loginFrameLayout)
                            .show(signUpFragment)
                            .commitAllowingStateLoss()
                    signUp?.setText("登录")
                    showFragmentStatus = Constant.SHOW_LOGIN
                }
            }
        }

    }

}
package com.android.wan.callback

import com.android.wan.net.response.LoginResponse

/**
 * @author by 有人@我 on 18/3/8.
 */
interface OnLoginSuccessListener {
    /**
     * 登录成功的回调函数
     */
    fun loginSuccess(loginResponse: LoginResponse)
}
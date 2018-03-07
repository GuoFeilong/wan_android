package com.android.wan.view

import com.android.wan.net.response.LoginResponse

/**
 * @author by 有人@我 on 18/3/7.
 */
interface SignUpView : BaseView {
    /**
     * 绑定注册
     */
    fun bindSignUpData(loginResponse: LoginResponse)
}

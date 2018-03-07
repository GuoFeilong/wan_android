package com.android.wan.view

import com.android.wan.net.response.LoginResponse

/**
 * @author by 有人@我 on 18/3/7.
 */
interface LoginView : BaseView {
    /**
     * 绑定登录数据
     */
    fun bindLoginData(loginResponse: LoginResponse)
}
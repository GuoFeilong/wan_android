package com.android.wan.net.response

/**
 * @author by 有人@我 on 18/3/6.
 */
data class LoginResponse(var errorCode: Int,
                         var errorMsg: String?,
                         var data: Data) {
    data class Data(var id: Int,
                    var username: String,
                    var password: String,
                    var icon: String?,
                    var type: Int,
                    var collectIds: List<Int>?)
}
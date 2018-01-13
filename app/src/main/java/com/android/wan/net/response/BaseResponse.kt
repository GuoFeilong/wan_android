package com.android.wan.net.response

/**
 * @author by 有人@我 on 2018/1/13.
 */
open class BaseResponse<T> {
    var errorCode: Int? = null
    var errorMsg: String? = null
    var data: T? = null
}
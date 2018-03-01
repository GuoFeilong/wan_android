package com.android.wan.net.response

/**
 * @author by 有人@我 on 18/3/1.
 */
data class HotKeyResponse(var errorCode: Int,
                          var errorMsg: String?,
                          var data: List<Data>?) {
    data class Data(var id: Int,
                    var name: String,
                    var link: Any,
                    var visible: Int,
                    var order: Int)
}
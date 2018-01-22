package com.android.wan.net.response

import com.android.wan.net.response.entity.Data

/**
 * @author by 有人@我 on 18/1/22.
 */
data class ArticleListResponse(var errorCode: Int,
                               var errorMsg: String?,
                               var data: Data)
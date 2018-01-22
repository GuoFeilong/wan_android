package com.android.wan.view

import com.android.wan.net.response.ArticleListResponse

/**
 * @author by 有人@我 on 18/1/22.
 */
interface ArticleListView : BaseView {
    /**
     * 绑定文章列表数据
     */
    fun bindArticleList(articleListResponse: ArticleListResponse)
}
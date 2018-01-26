package com.android.wan.callback

import com.android.wan.net.response.entity.Datas

/**
 * @author by 有人@我 on 2018/1/15.
 */
interface OnArticleClickListener<T> {
    /**
     * 文章列表条目点击事件
     */
    fun onRecyItemClick(position: Int, t: T)

    /**
     * 文章类型点击事件
     */
    fun onArticleTypeClick(article : Datas)

    /**
     * 文章作者点击事件
     */
    fun onArticleAuthClick(article :Datas)

    /**
     * 文章喜欢点击事件
     */
    fun onArticleLikeClick(article :Datas)

}
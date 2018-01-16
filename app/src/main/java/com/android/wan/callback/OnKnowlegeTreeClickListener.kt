package com.android.wan.callback

import com.android.wan.net.response.KnowledgeHierarchyResponse

/**
 * @author by 有人@我 on 18/1/16.
 */
interface OnKnowlegeTreeClickListener<T> {
    /**
     * 知识体系的点击事件
     */
    fun onRecyItemClick(position: Int, t: T)

    /**
     * 知识体系叶子点击事件
     */
    fun onKnowlegeTreeLeafClick(leaf: KnowledgeHierarchyResponse.Data.Children)

}
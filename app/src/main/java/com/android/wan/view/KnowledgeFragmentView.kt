package com.android.wan.view

import com.android.wan.net.response.KnowledgeHierarchyResponse

/**
 * @author by 有人@我 on 18/1/16.
 */
interface KnowledgeFragmentView : BaseView {
    /**
     * 绑定知识体系文章
     */
    fun bindknowledageTree(knowledgeHierarchyResponse: KnowledgeHierarchyResponse)
}
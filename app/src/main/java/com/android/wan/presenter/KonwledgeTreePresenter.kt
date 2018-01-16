package com.android.wan.presenter

import android.content.Context
import com.android.wan.callback.MvpCallback
import com.android.wan.moudle.KnowledgeTreeMoudle
import com.android.wan.net.response.KnowledgeHierarchyResponse
import com.android.wan.view.KnowledgeFragmentView

/**
 * @author by feilong on 18/1/16.
 */
class KonwledgeTreePresenter : BasePresenter<KnowledgeFragmentView>() {
    var knowledgeTreeMoudle: KnowledgeTreeMoudle = KnowledgeTreeMoudle()
    var knowledgeFragmentView: KnowledgeFragmentView? = null
    var activityContext: Context? = null

    override fun attachView(mvpView: KnowledgeFragmentView) {
        this.knowledgeFragmentView = mvpView
        this.activityContext = mvpView.getContext()
    }

    override fun detachView() {
        this.knowledgeFragmentView = null
    }

    override fun isViewAttached(): Boolean {
        return this.knowledgeFragmentView !== null
    }

    override fun getView(): KnowledgeFragmentView {
        return this.knowledgeFragmentView!!
    }

    fun getKnowledgeTree() {
        knowledgeFragmentView?.showLoading()
        knowledgeTreeMoudle.getKnowledgeTreeData(object : MvpCallback<KnowledgeHierarchyResponse> {
            override fun onSuccess(data: KnowledgeHierarchyResponse) {
                knowledgeFragmentView?.bindknowledageTree(data)
            }

            override fun onFailure(msg: String) {
                knowledgeFragmentView?.showToast(msg)
            }

            override fun onError() {
                knowledgeFragmentView?.showErr()
            }

            override fun onComplete() {
                knowledgeFragmentView?.hideLoading()
            }

            override fun showLoading() {
                knowledgeFragmentView?.showLoading()
            }

            override fun dissMissLoading() {
                knowledgeFragmentView?.hideLoading()
            }

        })

    }


}
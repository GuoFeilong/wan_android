package com.android.wan.fragment

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.wan.R
import com.android.wan.adapter.KnowledgeTreeAdapter
import com.android.wan.base.AbstractFragment
import com.android.wan.callback.OnKnowlegeTreeClickListener
import com.android.wan.net.response.KnowledgeHierarchyResponse
import com.android.wan.presenter.KonwledgeTreePresenter
import com.android.wan.view.KnowledgeFragmentView

/**
 * @author by 有人@我 on 2018/1/12.
 */
class KnowledgeHierarchyFragment : AbstractFragment(), KnowledgeFragmentView {
    var knowledgeTreePresenter: KonwledgeTreePresenter? = null
    var knowledgeTreeRecycler: RecyclerView? = null
    var knowledgeAdapter: KnowledgeTreeAdapter? = null


    override fun showLoading() {

    }

    override fun bindknowledageTree(knowledgeHierarchyResponse: KnowledgeHierarchyResponse) {
        when (knowledgeHierarchyResponse.errorCode) {
            0 -> {
                knowledgeAdapter?.knowledgeTrees = knowledgeHierarchyResponse.data
                knowledgeAdapter?.notifyDataSetChanged()
            }
        }
    }

    override fun hideLoading() {

    }

    override fun showToast(msg: String) {
        Toast.makeText(activityContext, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showErr() {
        Toast.makeText(activityContext, "请求错误", Toast.LENGTH_SHORT).show()
    }

    override fun getContext(): Context {
        return activityContext!!
    }

    override fun setFragmentLayout(): Int {
        return R.layout.fragment_knowledge
    }

    override fun initData() {
        knowledgeTreePresenter = KonwledgeTreePresenter()
        knowledgeTreePresenter?.attachView(this)
        knowledgeAdapter = KnowledgeTreeAdapter(activityContext!!)
        knowledgeAdapter?.knowlegetTreeClickListener = object : OnKnowlegeTreeClickListener<KnowledgeHierarchyResponse.Data> {
            override fun onRecyItemClick(position: Int, t: KnowledgeHierarchyResponse.Data) {
                Toast.makeText(activityContext, "树杈标题${t.name}", Toast.LENGTH_SHORT).show()
            }

            override fun onKnowlegeTreeLeafClick(leaf: KnowledgeHierarchyResponse.Data.Children) {
                Toast.makeText(activityContext, "叶子标题${leaf.name}", Toast.LENGTH_SHORT).show()
            }

        }

    }

    override fun initEvent() {
        knowledgeTreePresenter?.getKnowledgeTree()
    }

    override fun initView(rootView: View) {
        knowledgeTreeRecycler = rootView.findViewById(R.id.knowledgeTreeRecyclerView)
        knowledgeTreeRecycler?.layoutManager = LinearLayoutManager(activityContext)
        knowledgeTreeRecycler?.adapter = knowledgeAdapter
    }
}
package com.android.wan.fragment

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.android.wan.R
import com.android.wan.activity.ArticleListActivity
import com.android.wan.activity.BrowserActivity
import com.android.wan.adapter.KnowledgeTreeAdapter
import com.android.wan.base.AbstractFragment
import com.android.wan.callback.OnCarClickListener
import com.android.wan.callback.OnKnowlegeTreeClickListener
import com.android.wan.constant.Constant
import com.android.wan.customwidget.CarLayout
import com.android.wan.net.response.KnowledgeHierarchyResponse
import com.android.wan.net.response.entity.AriticleBundleData
import com.android.wan.net.response.entity.CarData
import com.android.wan.presenter.KonwledgeTreePresenter
import com.android.wan.view.KnowledgeFragmentView
import java.util.ArrayList

/**
 * @author by 有人@我 on 2018/1/12.
 */
class KnowledgeHierarchyFragment : AbstractFragment(), KnowledgeFragmentView {

    var knowledgeTreePresenter: KonwledgeTreePresenter? = null
    var knowledgeTreeRecycler: RecyclerView? = null
    var knowledgeAdapter: KnowledgeTreeAdapter? = null
    var carLayout: CarLayout? = null

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

    override fun bindCarData(carData: List<CarData>) {
        carLayout?.setCarsData(carData)
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
                val children: List<KnowledgeHierarchyResponse.Data.Children> = t.children!!
                val ariticleBundle = AriticleBundleData()
                val typeList: List<AriticleBundleData.AriticleTypeData> = ArrayList<AriticleBundleData.AriticleTypeData>()
                ariticleBundle.typeTitle = t.name
                for (leaf: KnowledgeHierarchyResponse.Data.Children in children) {
                    val typeTemp: AriticleBundleData.AriticleTypeData = AriticleBundleData.AriticleTypeData()
                    typeTemp.typeCid = leaf.id
                    typeTemp.typeName = leaf.name
                    (typeList as ArrayList).add(typeTemp)
                }
                ariticleBundle.typeList = typeList
                val intent = Intent(activityContext, ArticleListActivity::class.java)
                intent.putExtra(Constant.BUNDLE_KEY_4_ARITICLE_TYPE, ariticleBundle)
                startActivity(intent)
                Toast.makeText(activityContext, "树杈标题${t.name}", Toast.LENGTH_SHORT).show()
            }

            override fun onKnowlegeTreeLeafClick(leaf: KnowledgeHierarchyResponse.Data.Children) {
                val ariticleBundle = AriticleBundleData()
                val typeList: List<AriticleBundleData.AriticleTypeData> = ArrayList<AriticleBundleData.AriticleTypeData>()
                ariticleBundle.typeTitle = leaf.name
                val typeTemp: AriticleBundleData.AriticleTypeData = AriticleBundleData.AriticleTypeData()
                typeTemp.typeCid = leaf.id
                typeTemp.typeName = leaf.name
                (typeList as ArrayList).add(typeTemp)
                ariticleBundle.typeList = typeList
                val intent = Intent(activityContext, ArticleListActivity::class.java)
                intent.putExtra(Constant.BUNDLE_KEY_4_ARITICLE_TYPE, ariticleBundle)
                startActivity(intent)
                Toast.makeText(activityContext, "叶子标题${leaf.name}", Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun initEvent() {
        knowledgeTreePresenter?.getKnowledgeTree()
        knowledgeTreePresenter?.getCarData()
        carLayout?.setCarClickListener(object : OnCarClickListener {
            override fun carClick(carData: CarData) {
                val intent = Intent(activityContext, BrowserActivity::class.java)
                intent.putExtra(Constant.BUNDLE_KEY_4_WEB_TITLE, carData.carName)
                intent.putExtra(Constant.BUNDLE_KEY_4_WEB_URL, carData.carUrl)
                startActivity(intent)
            }
        })
    }

    override fun initView(rootView: View) {
        knowledgeTreeRecycler = rootView.findViewById(R.id.knowledgeTreeRecyclerView)
        carLayout = rootView.findViewById(R.id.carLayout)
        knowledgeTreeRecycler?.layoutManager = LinearLayoutManager(activityContext)
        knowledgeTreeRecycler?.adapter = knowledgeAdapter
    }

    fun onpenCar() {
        if (!carLayout!!.carRunState) {
            carLayout?.openCar()
        }
    }
}
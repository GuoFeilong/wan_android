package com.android.wan.activity

import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import com.android.wan.R
import com.android.wan.adapter.ArticleFragmentAdapter
import com.android.wan.base.AbstractActivity
import com.android.wan.constant.Constant
import com.android.wan.fragment.ArticleListFragment
import com.android.wan.net.response.entity.AriticleBundleData
import java.util.ArrayList

/**
 * @author by 有人@我 on 18/1/22.
 */
class ArticleListActivity : AbstractActivity() {
    var articleTabLayout: TabLayout? = null
    var articleViewPager: ViewPager? = null
    var toolbar: Toolbar? = null
    var articleBundleData: AriticleBundleData? = null
    var articleTtile: String? = null
    var articleFragments: List<ArticleListFragment>? = null
    var articleFragmentAdapter: ArticleFragmentAdapter? = null

    override fun initData() {
        articleBundleData = intent.getSerializableExtra(Constant.BUNDLE_KEY_4_ARITICLE_TYPE) as AriticleBundleData
        articleTtile = articleBundleData?.typeTitle
        articleFragments = ArrayList<ArticleListFragment>()
        articleFragmentAdapter = ArticleFragmentAdapter(supportFragmentManager)
    }

    override fun initEvent() {
        articleTabLayout?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {

            }

        })
        articleViewPager?.adapter = articleFragmentAdapter
    }

    override fun setContentLayoutId(): Int {
        return R.layout.activity_article_list
    }

    override fun initView() {
        articleTabLayout = findViewById(R.id.ariticleTabLayout)
        articleViewPager = findViewById(R.id.ariticleViewPager)
        toolbar = findViewById(R.id.toolbar)
        toolbar?.setTitle(articleTtile)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        creatTabs()
        articleFragmentAdapter?.articleFragments = articleFragments
        articleTabLayout?.setupWithViewPager(articleViewPager)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private fun creatTabs() {
        val typeList: List<AriticleBundleData.AriticleTypeData>? = articleBundleData?.typeList
        if (typeList !== null && typeList.size > 0) {
            for (tempData: AriticleBundleData.AriticleTypeData in typeList) {
                val tab: TabLayout.Tab = articleTabLayout!!.newTab()
                tab.text = tempData.typeName
                tab.setTag(tempData)
                articleTabLayout?.addTab(tab)

                val articleFragment = ArticleListFragment(tempData.typeName!!, tempData.typeCid)
                articleFragment.articleTitle = tempData.typeName!!
                (articleFragments as ArrayList).add(articleFragment)
            }
        }
    }
}
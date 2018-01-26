package com.android.wan.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.android.wan.R
import com.android.wan.activity.ArticleListActivity
import com.android.wan.activity.BrowserActivity
import com.android.wan.adapter.ArticleAdapter
import com.android.wan.base.AbstractFragment
import com.android.wan.callback.OnArticleClickListener
import com.android.wan.constant.Constant
import com.android.wan.net.response.ArticleListResponse
import com.android.wan.net.response.entity.AriticleBundleData
import com.android.wan.net.response.entity.Datas
import com.android.wan.presenter.ArticleListPresenter
import com.android.wan.view.ArticleListView
import com.jcodecraeer.xrecyclerview.ProgressStyle
import com.jcodecraeer.xrecyclerview.XRecyclerView
import java.util.ArrayList

@SuppressLint("ValidFragment")
/**
 * @author by 有人@我 on 18/1/26.
 */
class ArticleListFragment(var articleTitle: String, var cid: Int) : AbstractFragment(), ArticleListView {

    var articleAdapter: ArticleAdapter? = null
    var pageIndex: Int = 0
    var refresh: Boolean = false
    var articleRecycler: XRecyclerView? = null
    var articlePresenter: ArticleListPresenter? = null
    var articleName: String? = articleTitle

    override fun showLoading() {

    }

    override fun bindArticleList(articleListResponse: ArticleListResponse) {
        when (articleListResponse.errorCode) {
            0 -> {
                articleAdapter?.setArticleData(refresh, articleListResponse.data.datas!!)
                articleAdapter?.articleClickListener = object : OnArticleClickListener<Datas> {
                    override fun onRecyItemClick(position: Int, t: Datas) {
                        val intent = Intent(activityContext, BrowserActivity::class.java)
                        intent.putExtra(Constant.BUNDLE_KEY_4_WEB_TITLE, t.title)
                        intent.putExtra(Constant.BUNDLE_KEY_4_WEB_URL, t.link)
                        startActivity(intent)
                    }

                    override fun onArticleTypeClick(article: Datas) {
                        val ariticleBundle = AriticleBundleData()
                        val typeList: List<AriticleBundleData.AriticleTypeData> = ArrayList<AriticleBundleData.AriticleTypeData>()
                        ariticleBundle.typeTitle = article.chapterName
                        val typeTemp: AriticleBundleData.AriticleTypeData = AriticleBundleData.AriticleTypeData()
                        typeTemp.typeCid = article.id
                        typeTemp.typeName = article.chapterName
                        (typeList as ArrayList).add(typeTemp)
                        ariticleBundle.typeList = typeList
                        val intent = Intent(activityContext, ArticleListActivity::class.java)
                        intent.putExtra(Constant.BUNDLE_KEY_4_ARITICLE_TYPE, ariticleBundle)
                        startActivity(intent)
                    }

                    override fun onArticleAuthClick(article: Datas) {
                        Toast.makeText(activityContext, "作者", Toast.LENGTH_SHORT).show()
                    }

                    override fun onArticleLikeClick(article: Datas) {
                        Toast.makeText(activityContext, "收藏", Toast.LENGTH_SHORT).show()
                    }

                }
                articleAdapter?.notifyDataSetChanged()
                if (refresh) {
                    articleRecycler?.refreshComplete()
                } else {
                    articleRecycler?.loadMoreComplete()
                }
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
        return R.layout.fragment_article_list
    }

    override fun initData() {
        articlePresenter = ArticleListPresenter()
        articlePresenter?.attachView(this)
        articleAdapter = ArticleAdapter(activityContext!!)
    }

    override fun initEvent() {
        articlePresenter?.getArticleListData(pageIndex, cid)
        articleRecycler?.layoutManager = LinearLayoutManager(activityContext)

        articleRecycler?.setPullRefreshEnabled(true)
        articleRecycler?.setLoadingMoreEnabled(true)
        articleRecycler?.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader)
        articleRecycler?.setLoadingMoreProgressStyle(ProgressStyle.LineScalePulseOutRapid)
        articleRecycler?.adapter = articleAdapter
        articleRecycler?.setLoadingListener(object : XRecyclerView.LoadingListener {
            override fun onLoadMore() {
                pageIndex++
                refresh = false
                articlePresenter?.getArticleListData(pageIndex, cid)
            }

            override fun onRefresh() {
                refresh = true
                pageIndex = 0
                articlePresenter?.getArticleListData(pageIndex, cid)
            }
        })
    }

    override fun initView(rootView: View) {
        articleRecycler = rootView.findViewById(R.id.articleRecycler)
    }

}

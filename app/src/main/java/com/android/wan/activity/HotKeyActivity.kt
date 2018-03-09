package com.android.wan.activity

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import android.widget.Toast
import com.android.wan.R
import com.android.wan.adapter.ArticleAdapter
import com.android.wan.base.AbstractActivity
import com.android.wan.callback.OnArticleClickListener
import com.android.wan.constant.Constant
import com.android.wan.customwidget.FlowLayout
import com.android.wan.net.response.HomeListResponse
import com.android.wan.net.response.HotKeyResponse
import com.android.wan.net.response.entity.AriticleBundleData
import com.android.wan.net.response.entity.Datas
import com.android.wan.presenter.HotSearchPresenter
import com.android.wan.presenter.LikeAndUnLikePresenter
import com.android.wan.utils.HtmlUtil
import com.android.wan.utils.SharedPreferencesUtil
import com.android.wan.view.HotSearchView
import com.android.wan.view.LikeAndUnLikeView
import com.jcodecraeer.xrecyclerview.ProgressStyle
import com.jcodecraeer.xrecyclerview.XRecyclerView
import java.util.*

/**
 * @author by 有人@我 on 18/3/5.
 */
class HotKeyActivity : AbstractActivity(), HotSearchView, LikeAndUnLikeView {
    override fun bindLikeAction(homeListResponse: HomeListResponse) {
        articleAdapter?.likeItemByPosition(likePosition)
        Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show()

    }

    override fun bindUnLikeAction(homeListResponse: HomeListResponse) {
        articleAdapter?.unLikeItemByPosition(likePosition)
        Toast.makeText(this, "取消收藏", Toast.LENGTH_SHORT).show()
    }


    var toolbar: Toolbar? = null
    var hotKeyFlow: FlowLayout? = null
    var hotKeySearchResult: XRecyclerView? = null
    var hotSearchPresenter: HotSearchPresenter? = null
    var articleAdapter: ArticleAdapter? = null
    var pageIndex: Int = 0
    var refresh: Boolean = false
    var clearFlag: Boolean = false
    var currentSearchKey: String = ""
    var likePosition: Int = -1
    var likeAndUnLikePresenter: LikeAndUnLikePresenter? = null


    override fun initData() {
        articleAdapter = ArticleAdapter(this)
        likeAndUnLikePresenter = LikeAndUnLikePresenter()
        likeAndUnLikePresenter?.attachView(this)
        hotSearchPresenter = HotSearchPresenter()
        hotSearchPresenter?.attachView(this)
    }

    override fun initEvent() {
        hotSearchPresenter?.getHotSearchKeys()
        hotKeySearchResult?.layoutManager = LinearLayoutManager(this)

        hotKeySearchResult?.setPullRefreshEnabled(true)
        hotKeySearchResult?.setLoadingMoreEnabled(true)
        hotKeySearchResult?.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader)
        hotKeySearchResult?.setLoadingMoreProgressStyle(ProgressStyle.LineScalePulseOutRapid)
        hotKeySearchResult?.adapter = articleAdapter
        hotKeySearchResult?.setLoadingListener(object : XRecyclerView.LoadingListener {
            override fun onLoadMore() {
                pageIndex++
                refresh = false
                hotSearchPresenter?.getHotSearchResult(pageIndex, currentSearchKey)
            }

            override fun onRefresh() {
                refresh = true
                pageIndex = 0
                hotSearchPresenter?.getHotSearchResult(pageIndex, currentSearchKey)
            }
        })
    }

    override fun setContentLayoutId(): Int {
        return R.layout.activity_hot_key_search
    }

    override fun initView() {
        hotKeyFlow = findViewById(R.id.fl_hot_key)
        toolbar = findViewById(R.id.toolbar)
        hotKeySearchResult = findViewById(R.id.xrv_search_list)
        toolbar?.setTitle("热搜排行")
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        applyStatusBarColor(R.color.color_78AAF9)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }


    override fun showLoading() {

    }

    override fun bindHotSearchTitle(hotKeyResponse: HotKeyResponse) {
        when (hotKeyResponse.errorCode) {
            0 -> {
                val knowlegeFlowTitles: List<String> = ArrayList<String>()
                val childrens: ArrayList<HotKeyResponse.Data> = hotKeyResponse.data as ArrayList<HotKeyResponse.Data>

                for (temp: HotKeyResponse.Data in childrens) {
                    (knowlegeFlowTitles as ArrayList).add(temp.name)
                }

                hotKeyFlow?.removeAllViews()
                hotKeyFlow?.addViewText(knowlegeFlowTitles)

                // 初始化默认请求第一个
                hotSearchPresenter?.getHotSearchResult(pageIndex, childrens.get(0).name)

                hotKeyFlow?.setFlowChildClickListener(object : FlowLayout.OnFlowChildClickListener {
                    override fun onChildClick(position: Int) {
                        clearFlag = true
                        currentSearchKey = childrens.get(position).name
                        pageIndex = 0
                        hotSearchPresenter?.getHotSearchResult(pageIndex, childrens.get(position).name)
                    }
                })
            }
        }
    }

    override fun bindHotSearchResult(hotSearchResult: HomeListResponse) {
        when (hotSearchResult.errorCode) {
            0 -> {
                when (hotSearchResult.errorCode) {
                    0 -> {
                        if (clearFlag) {
                            articleAdapter?.clearArticleData()
                        }
                        articleAdapter?.setArticleData(refresh, hotSearchResult.data.datas!!)
                        articleAdapter?.articleClickListener = object : OnArticleClickListener<Datas> {
                            override fun onRecyItemClick(position: Int, t: Datas) {
                                val intent = Intent(this@HotKeyActivity, BrowserActivity::class.java)
                                intent.putExtra(Constant.BUNDLE_KEY_4_WEB_TITLE, HtmlUtil.htmlRemoveTag(t.title))
                                intent.putExtra(Constant.BUNDLE_KEY_4_WEB_URL, t.link)
                                startActivity(intent)
                            }

                            override fun onArticleTypeClick(article: Datas) {
                                val ariticleBundle = AriticleBundleData()
                                val typeList: List<AriticleBundleData.AriticleTypeData> = ArrayList<AriticleBundleData.AriticleTypeData>()
                                ariticleBundle.typeTitle = article.chapterName
                                val typeTemp: AriticleBundleData.AriticleTypeData = AriticleBundleData.AriticleTypeData()
                                typeTemp.typeCid = article.chapterId
                                typeTemp.typeName = article.chapterName
                                (typeList as ArrayList).add(typeTemp)
                                ariticleBundle.typeList = typeList
                                val intent = Intent(this@HotKeyActivity, ArticleListActivity::class.java)
                                intent.putExtra(Constant.BUNDLE_KEY_4_ARITICLE_TYPE, ariticleBundle)
                                startActivity(intent)
                            }

                            override fun onArticleAuthClick(article: Datas) {
                                Toast.makeText(this@HotKeyActivity, "作者", Toast.LENGTH_SHORT).show()
                            }

                            override fun onArticleLikeClick(article: Datas) {
                                if (SharedPreferencesUtil.login(this@HotKeyActivity)) {
                                    likePosition = article.postion
                                    if (article.collect) {
                                        likeAndUnLikePresenter?.unLikeAction(article.id, article.originId)
                                    } else {
                                        likeAndUnLikePresenter?.likeAction(article.id)
                                    }

                                } else {
                                    Toast.makeText(this@HotKeyActivity, "请先登录哦~(づ￣3￣)づ╭❤～", Toast.LENGTH_SHORT).show()
                                }
                            }

                        }
                        articleAdapter?.notifyDataSetChanged()
                        if (refresh) {
                            hotKeySearchResult?.refreshComplete()
                        } else {
                            hotKeySearchResult?.loadMoreComplete()
                        }
                        clearFlag = false
                    }
                }

            }
        }
    }

    override fun hideLoading() {

    }

    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showErr() {
        Toast.makeText(this, "请求错误", Toast.LENGTH_SHORT).show()
    }

    override fun getContext(): Context {
        return this
    }

}

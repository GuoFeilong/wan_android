package com.android.wan.activity

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.widget.Toast
import com.android.wan.R
import com.android.wan.adapter.ArticleAdapter
import com.android.wan.base.AbstractActivity
import com.android.wan.callback.OnArticleClickListener
import com.android.wan.constant.Constant
import com.android.wan.net.response.HomeListResponse
import com.android.wan.net.response.entity.AriticleBundleData
import com.android.wan.net.response.entity.Datas
import com.android.wan.presenter.LikeAndUnLikePresenter
import com.android.wan.presenter.LikeListPresenter
import com.android.wan.utils.HtmlUtil
import com.android.wan.utils.SharedPreferencesUtil
import com.android.wan.view.LikeAndUnLikeView
import com.android.wan.view.LikeListView
import com.jcodecraeer.xrecyclerview.ProgressStyle
import com.jcodecraeer.xrecyclerview.XRecyclerView
import java.util.ArrayList

/**
 * @author by 有人@我 on 18/3/9.
 */
class LikeListActivity : AbstractActivity(), LikeListView, LikeAndUnLikeView {

    override fun bindLikeAction(homeListResponse: HomeListResponse) {
        articleAdapter?.likeItemByPosition(likePosition)
        Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show()

    }

    override fun bindUnLikeAction(homeListResponse: HomeListResponse) {
        articleAdapter?.unLikeItemByPositionDelete(likePosition)
        Toast.makeText(this, "取消收藏", Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {

    }

    override fun bindMyLikeList(homeListResponse: HomeListResponse) {
        when (homeListResponse.errorCode) {
            0 -> {
                articleAdapter?.setArticleData(refresh, homeListResponse.data.datas!!)
                articleAdapter?.makeAllItemLike()
                articleAdapter?.articleClickListener = object : OnArticleClickListener<Datas> {
                    override fun onRecyItemClick(position: Int, t: Datas) {
                        val intent = Intent(this@LikeListActivity, BrowserActivity::class.java)
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
                        val intent = Intent(this@LikeListActivity, ArticleListActivity::class.java)
                        intent.putExtra(Constant.BUNDLE_KEY_4_ARITICLE_TYPE, ariticleBundle)
                        startActivity(intent)
                    }

                    override fun onArticleAuthClick(article: Datas) {
                        Toast.makeText(this@LikeListActivity, "作者", Toast.LENGTH_SHORT).show()
                    }

                    override fun onArticleLikeClick(article: Datas) {
                        if (SharedPreferencesUtil.login(this@LikeListActivity)) {
                            likePosition = article.postion
                            if (article.collect) {
                                likeAndUnLikePresenter?.unLikeAction(article.id, article.originId)
                            } else {
                                likeAndUnLikePresenter?.likeAction(article.id)
                            }

                        } else {
                            Toast.makeText(this@LikeListActivity, "请先登录哦~(づ￣3￣)づ╭❤～", Toast.LENGTH_SHORT).show()
                        }
                    }

                }
                articleAdapter?.notifyDataSetChanged()
                if (refresh) {
                    likeList?.refreshComplete()
                } else {
                    likeList?.loadMoreComplete()
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

    var toolbar: Toolbar? = null
    var likeList: XRecyclerView? = null
    var articleAdapter: ArticleAdapter? = null
    var pageIndex: Int = 0
    var refresh: Boolean = false
    var likeListPresenter: LikeListPresenter? = null
    var likePosition: Int = -1
    var likeAndUnLikePresenter: LikeAndUnLikePresenter? = null


    override fun initData() {
        likeListPresenter = LikeListPresenter()
        likeAndUnLikePresenter = LikeAndUnLikePresenter()
        likeListPresenter?.attachView(this)
        likeAndUnLikePresenter?.attachView(this)
        articleAdapter = ArticleAdapter(this)
    }

    override fun initEvent() {
        likeListPresenter?.getLikeList(pageIndex)
        likeList?.layoutManager = LinearLayoutManager(this)

        likeList?.setPullRefreshEnabled(true)
        likeList?.setLoadingMoreEnabled(true)
        likeList?.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader)
        likeList?.setLoadingMoreProgressStyle(ProgressStyle.LineScalePulseOutRapid)
        likeList?.adapter = articleAdapter
        likeList?.setLoadingListener(object : XRecyclerView.LoadingListener {
            override fun onLoadMore() {
                pageIndex++
                refresh = false
                likeListPresenter?.getLikeList(pageIndex)
            }

            override fun onRefresh() {
                refresh = true
                pageIndex = 0
                likeListPresenter?.getLikeList(pageIndex)
            }
        })

    }

    override fun setContentLayoutId(): Int {
        return R.layout.activity_my_like_list
    }

    override fun initView() {
        toolbar = findViewById(R.id.toolbar)
        likeList = findViewById(R.id.xrv_search_list)
        toolbar?.setTitle("我的收藏")
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        applyStatusBarColor(R.color.color_78AAF9)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }


}
package com.android.wan.fragment

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.android.wan.R
import com.android.wan.adapter.ArticleAdapter
import com.android.wan.adapter.BannerAdapter
import com.android.wan.base.AbstractFragment
import com.android.wan.callback.OnArticleClickListener
import com.android.wan.callback.OnRecyItemClickListener
import com.android.wan.net.response.BannerResponse
import com.android.wan.net.response.HomeListResponse
import com.android.wan.net.response.entity.Datas
import com.android.wan.presenter.HomeFragmentPresenter
import com.android.wan.view.HomeFragmentView
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * @author by 有人@我 on 2018/1/12.
 */
class HomeFragment : AbstractFragment(), HomeFragmentView {
    var bannerAdapter: BannerAdapter? = null
    var articleAdapter: ArticleAdapter? = null

    override fun showLoading() {
        Toast.makeText(activityContext, "显示loading", Toast.LENGTH_SHORT).show()
    }

    override fun bindBannerData(bannerResponse: BannerResponse) {
        Log.e("--轮播-->", bannerResponse.toString())

        bannerAdapter = BannerAdapter(activityContext!!)
        bannerAdapter!!.itemClickListener = object : OnRecyItemClickListener<BannerResponse.Data> {
            override fun onRecyItemClick(position: Int, t: BannerResponse.Data) {
                Toast.makeText(activityContext, t.desc, Toast.LENGTH_SHORT).show()
            }
        }
        bannerAdapter?.mContext = activityContext
        bannerAdapter?.bannerData = bannerResponse.data
        bannerRecycler.layoutManager = LinearLayoutManager(activityContext, LinearLayoutManager.HORIZONTAL, false)
        bannerRecycler.adapter = bannerAdapter
        bannerRecycler.bannerStart()

    }

    override fun bindHomeArticle(articleResponse: HomeListResponse) {
        Log.e("--首页-->", articleResponse.toString())

        when (articleResponse.errorCode) {
            0 -> {
                articleAdapter = ArticleAdapter(activityContext!!)
                articleAdapter?.articleList = articleResponse.data.datas!!
                articleAdapter?.articleClickListener = object : OnArticleClickListener<Datas> {
                    override fun onRecyItemClick(position: Int, t: Datas) {
                        Toast.makeText(activityContext, t.author, Toast.LENGTH_SHORT).show()
                    }

                    override fun onArticleTypeClick() {
                        Toast.makeText(activityContext, "类别", Toast.LENGTH_SHORT).show()
                    }

                    override fun onArticleAuthClick() {
                        Toast.makeText(activityContext, "作者", Toast.LENGTH_SHORT).show()
                    }

                    override fun onArticleLikeClick() {
                        Toast.makeText(activityContext, "收藏", Toast.LENGTH_SHORT).show()
                    }

                }
                articleRecycler.layoutManager = LinearLayoutManager(activityContext)
                articleRecycler.adapter = articleAdapter
            }
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun hideLoading() {
        Toast.makeText(activityContext, "隐藏loading", Toast.LENGTH_SHORT).show()
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

    var homeFragmentPresenter: HomeFragmentPresenter? = null

    override fun setFragmentLayout(): Int {
        return R.layout.fragment_home
    }

    override fun initData() {
        homeFragmentPresenter = HomeFragmentPresenter()
        homeFragmentPresenter?.attachView(this)
    }

    override fun initEvent() {
        homeFragmentPresenter?.getBannerData()
        homeFragmentPresenter?.getHomeListArticle(0)
    }

}
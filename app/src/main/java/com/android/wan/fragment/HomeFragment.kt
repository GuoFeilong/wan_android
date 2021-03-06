package com.android.wan.fragment

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.android.wan.R
import com.android.wan.activity.ArticleListActivity
import com.android.wan.activity.BrowserActivity
import com.android.wan.adapter.ArticleAdapter
import com.android.wan.adapter.BannerAdapter
import com.android.wan.base.AbstractFragment
import com.android.wan.callback.OnArticleClickListener
import com.android.wan.constant.Constant
import com.android.wan.net.response.BannerResponse
import com.android.wan.net.response.HomeListResponse
import com.android.wan.net.response.entity.AriticleBundleData
import com.android.wan.net.response.entity.Datas
import com.android.wan.presenter.HomeFragmentPresenter
import com.android.wan.presenter.LikeAndUnLikePresenter
import com.android.wan.utils.SharedPreferencesUtil
import com.android.wan.view.HomeFragmentView
import com.android.wan.view.LikeAndUnLikeView
import com.bumptech.glide.Glide
import com.jcodecraeer.xrecyclerview.ProgressStyle
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.listener.OnBannerListener
import com.youth.banner.loader.ImageLoader


/**
 * @author by 有人@我 on 2018/1/12.
 */
class HomeFragment : AbstractFragment(), HomeFragmentView, LikeAndUnLikeView {

    override fun bindLikeAction(homeListResponse: HomeListResponse) {
        articleAdapter?.likeItemByPosition(likePosition)
        Toast.makeText(activityContext, "收藏成功", Toast.LENGTH_SHORT).show()

    }

    override fun bindUnLikeAction(homeListResponse: HomeListResponse) {
        articleAdapter?.unLikeItemByPosition(likePosition)
        Toast.makeText(activityContext, "取消收藏", Toast.LENGTH_SHORT).show()
    }

    var banner: Banner? = null
    var articleRecycler: XRecyclerView? = null
    var bannerAdapter: BannerAdapter? = null
    var articleAdapter: ArticleAdapter? = null
    var pageIndex: Int = 0
    var refresh: Boolean = false
    var likePosition: Int = -1

    override fun showLoading() {

    }

    override fun bindBannerData(bannerResponse: BannerResponse) {
        val soHotPic = "http://www.zhlzw.com/UploadFiles/Article_UploadFiles/201204/20120422013455474.JPG"
        val soHotDesc = "隐藏N年的Jsoup学习资料,性趣是最好的老师"
        val soHotGitHub = "https://github.com/GuoFeilong/SoHOT"

        val soHotData: BannerResponse.Data = BannerResponse.Data(9, soHotGitHub, soHotPic, soHotDesc, soHotDesc, 0, 0, 0)
        val list = bannerResponse.data
        (list as ArrayList<BannerResponse.Data>).add(0, soHotData)

        val titles = ArrayList<String>()
        val bannerUrls = ArrayList<String>()

        for (item: BannerResponse.Data in list) {
            val title = item.desc
            val urlPic = item.imagePath
            titles.add(title)
            bannerUrls.add(urlPic)
        }

        banner?.setBannerTitles(titles)
        banner?.setImages(bannerUrls)
        banner?.setOnBannerListener(object : OnBannerListener {
            override fun OnBannerClick(position: Int) {
                val data = list.get(position)
                val intent = Intent(activityContext, BrowserActivity::class.java)
                intent.putExtra(Constant.BUNDLE_KEY_4_WEB_TITLE, data.title)
                intent.putExtra(Constant.BUNDLE_KEY_4_WEB_URL, data.url)
                startActivity(intent)
            }

        })
        banner?.start()
    }

    override fun bindHomeArticle(articleResponse: HomeListResponse) {

        when (articleResponse.errorCode) {
            0 -> {
                articleAdapter?.setArticleData(refresh, articleResponse.data.datas!!)
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
                        typeTemp.typeCid = article.chapterId
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
                        if (SharedPreferencesUtil.login(activityContext!!)) {
                            likePosition = article.postion
                            if (article.collect) {
                                likeAndUnLikePresenter?.unLikeAction(article.id, article.originId)
                            } else {
                                likeAndUnLikePresenter?.likeAction(article.id)
                            }

                        } else {
                            Toast.makeText(activityContext, "请先登录哦~(づ￣3￣)づ╭❤～", Toast.LENGTH_SHORT).show()
                        }
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

    override fun onAttach(context: Context?) {
        super.onAttach(context)
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

    var homeFragmentPresenter: HomeFragmentPresenter? = null
    var likeAndUnLikePresenter: LikeAndUnLikePresenter? = null

    override fun setFragmentLayout(): Int {
        return R.layout.fragment_home
    }

    override fun initData() {
        homeFragmentPresenter = HomeFragmentPresenter()
        likeAndUnLikePresenter = LikeAndUnLikePresenter()
        homeFragmentPresenter?.attachView(this)
        likeAndUnLikePresenter?.attachView(this)
        bannerAdapter = BannerAdapter(activityContext!!)
        articleAdapter = ArticleAdapter(activityContext!!)

    }

    override fun initView(rootView: View) {
        banner = rootView.findViewById(R.id.bannerRecycler)
        banner?.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
        banner?.setBannerAnimation(Transformer.DepthPage)
        banner?.setImageLoader(GlideImageLoader())
        banner?.isAutoPlay(true)
        banner?.setDelayTime(5000)
        articleRecycler = rootView.findViewById(R.id.articleRecycler)
    }

    override fun initEvent() {
        homeFragmentPresenter?.getBannerData()
        homeFragmentPresenter?.getHomeListArticle(pageIndex)
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
                homeFragmentPresenter?.getHomeListArticle(pageIndex)
            }

            override fun onRefresh() {
                refresh = true
                pageIndex = 0
                homeFragmentPresenter?.getHomeListArticle(pageIndex)
            }
        })

    }

    class GlideImageLoader : ImageLoader() {
        override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
            Glide.with(context!!).load(path).into(imageView!!);
        }
    }
}
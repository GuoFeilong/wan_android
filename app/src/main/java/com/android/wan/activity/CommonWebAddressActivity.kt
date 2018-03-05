package com.android.wan.activity

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.widget.Toast
import com.android.wan.R
import com.android.wan.adapter.CommonWebAddressAdapter
import com.android.wan.base.AbstractActivity
import com.android.wan.callback.OnRecyItemClickListener
import com.android.wan.constant.Constant
import com.android.wan.net.response.FriendListResponse
import com.android.wan.presenter.CommonWebSitePresenter
import com.android.wan.view.CommonWebSiteView
import com.jcodecraeer.xrecyclerview.XRecyclerView

/**
 * @author by 有人@我 on 18/3/5.
 */
class CommonWebAddressActivity : AbstractActivity(), CommonWebSiteView {
    var toolbar: Toolbar? = null
    var commonWebAddress: XRecyclerView? = null
    var commonWebSitePresenter: CommonWebSitePresenter? = null
    var commonWebAddressAdapter: CommonWebAddressAdapter? = null

    override fun initData() {
        commonWebSitePresenter = CommonWebSitePresenter()
        commonWebSitePresenter?.attachView(this)
        commonWebAddressAdapter = CommonWebAddressAdapter()
    }

    override fun initEvent() {
        commonWebSitePresenter?.getCommonWebSite()
        commonWebAddress?.layoutManager = LinearLayoutManager(this)
        commonWebAddress?.setPullRefreshEnabled(false)
        commonWebAddress?.setLoadingMoreEnabled(false)
        commonWebAddress?.adapter = commonWebAddressAdapter
    }

    override fun setContentLayoutId(): Int {
        return R.layout.activity_web_address
    }

    override fun initView() {
        toolbar = findViewById(R.id.toolbar)
        commonWebAddress = findViewById(R.id.commonWebAddress)
        toolbar?.setTitle("常用网站")
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

    override fun bindCommonWebSite(friendListResponse: FriendListResponse) {
        when (friendListResponse.errorCode) {
            0 -> {
                val myBlog = FriendListResponse.Data(0, "有人@我(自己插入的博客)", "http://blog.csdn.net/givemeacondom", 1, 1, "")
                val myGitHub = FriendListResponse.Data(0, "动画按钮(自定义View)", "http://blog.csdn.net/givemeacondom/article/details/51917379", 1, 1, "")

                val allWeb = friendListResponse.data
                (allWeb as ArrayList<FriendListResponse.Data>).add(0, myBlog)
                allWeb.add(1, myGitHub)
                commonWebAddressAdapter?.webAddressData = friendListResponse.data
                commonWebAddressAdapter?.notifyDataSetChanged()
                commonWebAddressAdapter?.itemClickListener = object : OnRecyItemClickListener<FriendListResponse.Data> {
                    override fun onRecyItemClick(position: Int, t: FriendListResponse.Data) {
                        val intent = Intent(this@CommonWebAddressActivity, BrowserActivity::class.java)
                        intent.putExtra(Constant.BUNDLE_KEY_4_WEB_TITLE, t.name)
                        intent.putExtra(Constant.BUNDLE_KEY_4_WEB_URL, t.link)
                        startActivity(intent)
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
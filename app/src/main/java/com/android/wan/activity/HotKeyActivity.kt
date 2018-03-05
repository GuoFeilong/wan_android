package com.android.wan.activity

import android.content.Context
import android.support.v7.widget.Toolbar
import android.util.Log
import android.widget.Toast
import com.android.wan.R
import com.android.wan.base.AbstractActivity
import com.android.wan.customwidget.FlowLayout
import com.android.wan.net.response.HomeListResponse
import com.android.wan.net.response.HotKeyResponse
import com.android.wan.presenter.HotSearchPresenter
import com.android.wan.view.HotSearchView
import java.util.*

/**
 * @author by 有人@我 on 18/3/5.
 */
class HotKeyActivity : AbstractActivity(), HotSearchView {

    var toolbar: Toolbar? = null
    var hotKeyFlow: FlowLayout? = null
    var hotSearchPresenter: HotSearchPresenter? = null

    override fun initData() {
        hotSearchPresenter = HotSearchPresenter()
        hotSearchPresenter?.attachView(this)
    }

    override fun initEvent() {
        hotSearchPresenter?.getHotSearchKeys()
    }

    override fun setContentLayoutId(): Int {
        return R.layout.activity_hot_key_search
    }

    override fun initView() {
        hotKeyFlow = findViewById(R.id.fl_hot_key)
        toolbar = findViewById(R.id.toolbar)
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

                hotKeyFlow?.setFlowChildClickListener(object : FlowLayout.OnFlowChildClickListener {
                    override fun onChildClick(position: Int) {
                        hotSearchPresenter?.getHotSearchResult(0, childrens.get(position).name)
                    }
                })
            }
        }
    }

    override fun bindHotSearchResult(hotSearchResult: HomeListResponse) {
        Log.e("搜索", "--->" + hotSearchResult.toString())
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

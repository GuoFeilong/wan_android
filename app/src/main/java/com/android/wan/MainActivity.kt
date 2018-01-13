package com.android.wan

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import com.android.wan.base.AbstractActivity
import com.android.wan.presenter.HomeMainPresenter
import com.android.wan.view.HomeMainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AbstractActivity(), HomeMainView {
    override fun showLoading() {

    }


    override fun hideLoading() {

    }

    override fun showToast(msg: String) {
    }

    override fun showErr() {
    }

    override fun getContext(): Context {
        return this
    }

    var homeMainPresenter: HomeMainPresenter? = null

    override fun initData() {
        homeMainPresenter= HomeMainPresenter()
        homeMainPresenter!!.attachView(this)
        homeMainPresenter!!.addAllFragment(R.id.fl_fragment_container)
        homeMainPresenter!!.showCurrentFragment(R.id.fl_fragment_container, 0)
    }

    override fun initEvent() {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun setContentLayoutId(): Int {
        return R.layout.activity_main
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                homeMainPresenter!!.showCurrentFragment(R.id.fl_fragment_container, 0)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                homeMainPresenter!!.showCurrentFragment(R.id.fl_fragment_container, 1)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                homeMainPresenter!!.showCurrentFragment(R.id.fl_fragment_container, 2)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applyTheme(R.color.colorPrimary)
    }

    override fun onDestroy() {
        super.onDestroy()
        homeMainPresenter!!.detachView()
    }
}

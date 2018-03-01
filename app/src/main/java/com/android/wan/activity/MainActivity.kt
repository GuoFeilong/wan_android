package com.android.wan.activity

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.content.ContextCompat
import com.android.wan.R
import com.android.wan.base.AbstractActivity
import com.android.wan.base.AbstractFragment
import com.android.wan.fragment.KnowledgeHierarchyFragment
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

    var homeMainPresenter: HomeMainPresenter = HomeMainPresenter()
    var currentFragment: AbstractFragment? = null

    override fun initData() {
        homeMainPresenter.attachView(this)
        homeMainPresenter.addAllFragment(R.id.fl_fragment_container)
        homeMainPresenter.showCurrentFragment(R.id.fl_fragment_container, 0)
    }

    override fun initView() {
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
                applyStatusBarColor(android.R.color.transparent)
                homeMainPresenter.showCurrentFragment(R.id.fl_fragment_container, 0)
                navigation.setBackgroundColor(ContextCompat.getColor(this@MainActivity, android.R.color.transparent))
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                applyStatusBarColor(R.color.color_ff8a65)
                navigation.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.color_ff8a65))
                currentFragment = homeMainPresenter.showCurrentFragment(R.id.fl_fragment_container, 1)
                (currentFragment as KnowledgeHierarchyFragment).onpenCar()

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                applyStatusBarColor(R.color.color_78AAF9)
                navigation.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.color_78AAF9))
                homeMainPresenter.showCurrentFragment(R.id.fl_fragment_container, 2)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        homeMainPresenter.detachView()
    }
}

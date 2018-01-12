package com.android.wan

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import com.android.wan.base.AbstractActivity
import com.android.wan.contract.TaskMainContract
import com.android.wan.presenter.TaskMainPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AbstractActivity(), TaskMainContract.View {


    override fun showProgressDialog() {

    }

    override fun dissMissProgressDialog() {

    }

    var taskMainPresenter: TaskMainPresenter? = null
    override fun initData() {
        taskMainPresenter = TaskMainPresenter(this)
        taskMainPresenter!!.addAllFragment(this, R.id.fl_fragment_container)
    }

    override fun initEvent() {
        taskMainPresenter!!.showCurrentFragment(this@MainActivity, 0)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun setContentLayoutId(): Int {
        return R.layout.activity_main
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                taskMainPresenter!!.showCurrentFragment(this@MainActivity, 0)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                taskMainPresenter!!.showCurrentFragment(this@MainActivity, 1)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                taskMainPresenter!!.showCurrentFragment(this@MainActivity, 2)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applyTheme(R.color.colorPrimary)
    }
}

package com.android.wan.presenter

import android.content.Context
import com.android.wan.base.AbstractActivity
import com.android.wan.moudle.HomeMainMoudle
import com.android.wan.presenter.BasePresenter
import com.android.wan.view.HomeMainView

/**
 * @author by Jsion on 2018/1/13.
 */
class HomeMainPresenter : BasePresenter<HomeMainView>() {
    var homeMainMoudle: HomeMainMoudle = HomeMainMoudle()
    var homeMainView: HomeMainView? = null
    var activityContex: Context? = null

    override fun attachView(mvpView: HomeMainView) {
        this.homeMainView = mvpView
        activityContex = mvpView.getContext()
    }

    override fun detachView() {
        this.homeMainView = null
    }

    override fun isViewAttached(): Boolean {
        return this.homeMainView !== null
    }

    override fun getView(): HomeMainView {
        return this.homeMainView!!
    }

    fun hideAllFragment() {
        for (allFragmentDatum in homeMainMoudle.getAllFragmentData()) {
            (activityContex as AbstractActivity).supportFragmentManager
                    .beginTransaction()
                    .hide(allFragmentDatum)
                    .commitAllowingStateLoss()
        }
    }

    fun addAllFragment(layoutId: Int) {
        for (allFragmentDatum in homeMainMoudle.getAllFragmentData()) {
            (activityContex as AbstractActivity).supportFragmentManager
                    .beginTransaction()
                    .add(layoutId, allFragmentDatum)
                    .commitAllowingStateLoss()
        }
    }

    fun showCurrentFragment(layoutId: Int, index: Int) {
        hideAllFragment()
        (activityContex as AbstractActivity).supportFragmentManager
                .beginTransaction()
                .add(layoutId, homeMainMoudle.getAllFragmentData().get(index))
                .commitAllowingStateLoss()
    }

}
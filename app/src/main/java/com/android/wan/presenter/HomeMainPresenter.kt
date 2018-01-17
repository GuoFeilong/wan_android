package com.android.wan.presenter

import android.content.Context
import com.android.wan.base.AbstractActivity
import com.android.wan.base.AbstractFragment
import com.android.wan.moudle.HomeMainMoudle
import com.android.wan.presenter.BasePresenter
import com.android.wan.view.HomeMainView

/**
 * @author by 有人@我 on 2018/1/13.
 */
class HomeMainPresenter : BasePresenter<HomeMainView>() {
    var homeMainMoudle: HomeMainMoudle = HomeMainMoudle()
    var homeMainView: HomeMainView? = null
    var activityContex: Context? = null
    var allFragmentData: List<AbstractFragment>? = homeMainMoudle.getAllFragmentData()

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
        for (allFragmentDatum in this.allFragmentData!!) {
            (activityContex as AbstractActivity).supportFragmentManager
                    .beginTransaction()
                    .hide(allFragmentDatum)
                    .commit()
        }
    }

    fun addAllFragment(layoutId: Int) {
        for (allFragmentDatum in this.allFragmentData!!) {
            (activityContex as AbstractActivity).supportFragmentManager
                    .beginTransaction()
                    .add(layoutId, allFragmentDatum)
                    .commit()
        }
    }

    fun showCurrentFragment(layoutId: Int, index: Int): AbstractFragment {
        hideAllFragment()
        (activityContex as AbstractActivity).supportFragmentManager
                .beginTransaction()
                .show(this.allFragmentData!!.get(index))
                .commit()
        return this.allFragmentData!!.get(index)
    }

}
package com.android.wan.presenter

import com.android.wan.view.BaseView

/**
 * @author by 有人@我 on 2018/1/13.
 */
abstract class BasePresenter<V : BaseView> {
    var mvpView: V? = null

    abstract fun attachView(mvpView: V)

    abstract fun detachView()

    abstract fun isViewAttached(): Boolean

    abstract fun getView(): V

}

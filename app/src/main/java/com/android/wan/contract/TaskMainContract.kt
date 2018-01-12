package com.android.wan.contract

import com.android.wan.base.AbstractActivity
import com.android.wan.base.BasePresenter
import com.android.wan.base.BaseView

/**
 * @author by 有人@我 on 2018/1/12.
 */
interface TaskMainContract {

    interface View : BaseView {

    }

    interface Presenter : BasePresenter {
        /**
         * 隐藏所有fragment
         */
        fun hideAllFragment(activity: AbstractActivity)

        /**
         * 添加所有fragment
         */
        fun addAllFragment(activity: AbstractActivity, fragmentContainerId: Int)

        /**
         * 显示正确的fragment
         */
        fun showCurrentFragment(activity: AbstractActivity, index: Int)
    }
}

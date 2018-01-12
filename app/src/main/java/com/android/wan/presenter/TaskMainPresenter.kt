package com.android.wan.presenter

import com.android.wan.base.AbstractActivity
import com.android.wan.base.AbstractFragment
import com.android.wan.contract.TaskMainContract
import com.android.wan.fragment.HomeFragment
import com.android.wan.fragment.KnowledgeHierarchyFragment
import com.android.wan.fragment.UserCenterFragment
import java.util.ArrayList

/**
 * @author by Jsion on 2018/1/12.
 */
class TaskMainPresenter(var taskMainView: TaskMainContract.View?) : TaskMainContract.Presenter {
    var fragmentList: List<AbstractFragment>? = null

    override fun start() {
        openTask()
    }

    override fun hideAllFragment(activity: AbstractActivity) {
        for (item: AbstractFragment in fragmentList!!) {
            activity.supportFragmentManager
                    .beginTransaction()
                    .hide(item)
                    .commitAllowingStateLoss()
        }
    }

    override fun addAllFragment(activity: AbstractActivity, fragmentContainerId: Int) {
        val homeFragment = HomeFragment()
        val knowledgeFragment = KnowledgeHierarchyFragment()
        val userCenterFragment = UserCenterFragment()

        fragmentList = ArrayList<AbstractFragment>()
        (fragmentList as ArrayList<AbstractFragment>).add(homeFragment)
        (fragmentList as ArrayList<AbstractFragment>).add(knowledgeFragment)
        (fragmentList as ArrayList<AbstractFragment>).add(userCenterFragment)

        activity.supportFragmentManager
                .beginTransaction()
                .add(fragmentContainerId, homeFragment)
                .add(fragmentContainerId, knowledgeFragment)
                .add(fragmentContainerId, userCenterFragment)
                .commitAllowingStateLoss()
    }

    override fun showCurrentFragment(activity: AbstractActivity, index: Int) {
        hideAllFragment(activity)
        activity.supportFragmentManager
                .beginTransaction()
                .show(fragmentList!!.get(index))
                .commitAllowingStateLoss()
    }

    /**
     * 开始任务
     */
    private fun openTask() {

    }

}
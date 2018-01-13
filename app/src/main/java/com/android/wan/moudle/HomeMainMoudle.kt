package com.android.wan.moudle

import com.android.wan.base.AbstractFragment
import com.android.wan.fragment.HomeFragment
import com.android.wan.fragment.KnowledgeHierarchyFragment
import com.android.wan.fragment.UserCenterFragment
import java.util.ArrayList

/**
 * @author by 有人@我 on 2018/1/13.
 */
class HomeMainMoudle {
    /**
     * 创建所有的fragment
     */
    fun getAllFragmentData(): List<AbstractFragment> {
        val homeFragment = HomeFragment()
        val knowledgeFragment = KnowledgeHierarchyFragment()
        val userCenterFragment = UserCenterFragment()
        val fragmentList = ArrayList<AbstractFragment>()
        fragmentList.add(homeFragment)
        fragmentList.add(knowledgeFragment)
        fragmentList.add(userCenterFragment)
        return fragmentList
    }
}
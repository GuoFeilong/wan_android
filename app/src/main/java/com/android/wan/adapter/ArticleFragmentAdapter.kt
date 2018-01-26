package com.android.wan.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.android.wan.fragment.ArticleListFragment

/**
 * @author by 有人@我 on 18/1/26.
 */
class ArticleFragmentAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {
    var articleFragments: List<ArticleListFragment>? = null

    override fun getItem(position: Int): Fragment {
        return articleFragments!!.get(position)
    }

    override fun getCount(): Int {
        return articleFragments!!.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return articleFragments?.get(position)?.articleTitle
    }

}
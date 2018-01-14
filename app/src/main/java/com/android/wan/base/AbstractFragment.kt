package com.android.wan.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * @author by 有人@我 on 2018/1/12.
 */
abstract class AbstractFragment : Fragment() {
    protected var activityContext: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = setFragmentRootView(inflater, container)
        initData()
        initView(rootView)
        initEvent()
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        activityContext = context
    }

    protected fun setFragmentRootView(inflater: LayoutInflater, container: ViewGroup?): View {
        return inflater.inflate(setFragmentLayout(), container, false)
    }

    protected abstract fun setFragmentLayout(): Int

    protected abstract fun initData()

    protected abstract fun initEvent()

    private fun initView(rootView: View) {

    }
}
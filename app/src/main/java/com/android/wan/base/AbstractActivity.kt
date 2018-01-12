package com.android.wan.base

import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import com.afollestad.aesthetic.Aesthetic
import com.afollestad.aesthetic.BottomNavBgMode
import com.afollestad.aesthetic.BottomNavIconTextMode
import com.gyf.barlibrary.ImmersionBar

/**
 * @author by 有人@我 on 2018/1/12.
 */
abstract class AbstractActivity : AppCompatActivity() {
    protected var mImmersionBar: ImmersionBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Aesthetic.attach(this);
        super.onCreate(savedInstanceState)
        mImmersionBar = ImmersionBar.with(this)
        mImmersionBar!!.init()
        initData()
        setContentView(setContentLayoutId())
        initView()
        initEvent()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        Aesthetic.pause(this);
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mImmersionBar!!.destroy()
    }

    protected abstract fun initData()

    protected abstract fun initEvent()

    protected abstract fun setContentLayoutId(): Int

    private fun initView() {
        ButterKnife.bind(this)
    }

    /**
     * 修改主体颜色
     */
    protected fun applyTheme(color: Int) {
        val covertColor = ContextCompat.getColor(this, color)
        Aesthetic.get()
                .colorPrimary(covertColor)
                .colorAccent(covertColor)
                .textColorSecondaryInverse(Color.WHITE)
                .colorStatusBarAuto()
                .colorNavigationBarAuto()
                .bottomNavigationBackgroundMode(BottomNavBgMode.PRIMARY_DARK)
                .bottomNavigationIconTextMode(BottomNavIconTextMode.BLACK_WHITE_AUTO)
                .apply()
        applyStatusBarColor(color)
    }

    /**
     * 修改状态栏颜色
     */
    protected fun applyStatusBarColor(color: Int) {
        mImmersionBar!!.statusBarColor(color)
                .statusBarAlpha(0.2F)
                .init()
    }
}
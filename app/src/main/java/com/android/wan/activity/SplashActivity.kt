package com.android.wan.activity

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.util.DisplayMetrics
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.ViewAnimator
import com.android.wan.R
import com.android.wan.base.AbstractActivity

/**
 * @author by 有人@我 on 18/3/9.
 */
class SplashActivity : AbstractActivity() {
    var logo: ImageView? = null
    var splashText: TextView? = null

    override fun initData() {

    }

    override fun initEvent() {
        splashText?.visibility = View.GONE
        windmillRotate()
    }

    override fun setContentLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initView() {
        logo = findViewById(R.id.iv_windmill)
        splashText = findViewById(R.id.tv_welcome_splash)
        applyStatusBarColor(android.R.color.transparent)
    }

    fun windmillRotate() {
        val rotate: ObjectAnimator = ObjectAnimator.ofFloat(logo, "rotation", 0f, 3600f)
        val animator = ObjectAnimator.ofFloat(logo, "translationY", 0.0f, screenHeight() * 0.34f)
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(rotate, animator)
        animatorSet.duration = 2000
        animatorSet.start()

        animatorSet.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                splashText?.visibility = View.VISIBLE
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                overridePendingTransition(R.anim.anim_goaway, R.anim.anim_start);
                finish()
            }

        })
    }

    fun screenHeight(): Int {
        val display = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(display)
        return display.heightPixels
    }
}
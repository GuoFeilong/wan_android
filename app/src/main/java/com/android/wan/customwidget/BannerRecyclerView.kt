package com.android.wan.customwidget

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.MotionEvent
import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * @author by 有人@我 on 2018/1/15.
 */
class BannerRecyclerView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null,
        defStyleAttr: Int = 0) : RecyclerView(context, attrs, defStyleAttr) {

    var subscriber: Subscription? = null
    var index: Int = 0
    var intervalTime: Long = 5

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {

        when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                bannerStop()
            }
            MotionEvent.ACTION_MOVE -> {

            }
            MotionEvent.ACTION_UP -> {
                bannerStart()
            }

        }
        return super.dispatchTouchEvent(ev)
    }


    fun bannerStart() {
        val itemCount = adapter?.itemCount
        subscriber?.unsubscribe()
        subscriber = Observable.interval(2, intervalTime, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    smoothScrollToPosition(index++)
                    if (index >= itemCount!!) {
                        index = 0
                    }
                }
    }

    fun bannerStop() {
        subscriber?.unsubscribe()
    }

}

package com.android.wan.application

import android.annotation.SuppressLint
import android.app.Application
import android.content.ComponentCallbacks2
import android.content.Context
import com.android.wan.base.Preference
import com.bumptech.glide.Glide


/**
 * @author by 有人@我 on 2018/1/12.
 */
class Global : Application() {

    companion object {
        var applicationContext: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        Preference.setContext(applicationContext)
        Global.Companion.applicationContext = this
    }

    @SuppressLint("SwitchIntDef")
    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        when (level) {
            ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN -> {
                Glide.get(this).clearMemory()
                Glide.get(this).trimMemory(level)
            }
        }
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Glide.get(this).clearMemory()
    }
}
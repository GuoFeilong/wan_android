package com.android.wan.activity

import android.support.v7.widget.Toolbar
import com.android.wan.R
import com.android.wan.base.AbstractActivity
import com.android.wan.constant.Constant
import com.android.wan.customwidget.Html5Webview

/**
 * @author by 有人@我 on 18/1/17.
 */
class BrowserActivity : AbstractActivity() {
    var toolbar: Toolbar? = null
    var browserTitle: String? = null
    var browserUrl: String? = null
    var webView: Html5Webview? = null

    override fun initData() {
        getBundleData()
    }

    override fun initEvent() {
        webView?.loadUrl(browserUrl)
    }

    override fun setContentLayoutId(): Int {
        return R.layout.activity_browser
    }

    override fun initView() {
        toolbar = findViewById(R.id.toolbar)
        webView = findViewById(R.id.webview)
        toolbar?.setTitle(browserTitle)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        applyStatusBarColor(R.color.color_ffcc80)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    fun getBundleData() {
        browserTitle = intent.getStringExtra(Constant.BUNDLE_KEY_4_WEB_TITLE)
        browserUrl = intent.getStringExtra(Constant.BUNDLE_KEY_4_WEB_URL)
    }
}

package com.android.wan.fragment

import android.content.Intent
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.android.wan.R
import com.android.wan.activity.BrowserActivity
import com.android.wan.activity.CommonWebAddressActivity
import com.android.wan.base.AbstractFragment
import com.android.wan.constant.Constant
import com.android.wan.customwidget.UserCenterItemView

/**
 * @author by 有人@我 on 2018/1/12.
 */
class UserCenterFragment : AbstractFragment(), View.OnClickListener {

    var loginOrRegist: TextView? = null
    var favorite: UserCenterItemView? = null
    var hotSearch: UserCenterItemView? = null
    var commonWebSite: UserCenterItemView? = null
    var jsonup: UserCenterItemView? = null
    var about: UserCenterItemView? = null

    override fun setFragmentLayout(): Int {
        return R.layout.fragment_user_center
    }

    override fun initData() {

    }

    override fun initEvent() {
        loginOrRegist?.setOnClickListener(this)
        favorite?.setOnClickListener(this)
        hotSearch?.setOnClickListener(this)
        commonWebSite?.setOnClickListener(this)
        jsonup?.setOnClickListener(this)
        about?.setOnClickListener(this)
    }

    override fun initView(rootView: View) {
        loginOrRegist = rootView.findViewById(R.id.tv_login_action)
        favorite = rootView.findViewById(R.id.civ_favorite)
        hotSearch = rootView.findViewById(R.id.civ_hot_search)
        commonWebSite = rootView.findViewById(R.id.civ_web_address)
        jsonup = rootView.findViewById(R.id.civ_so_hot)
        about = rootView.findViewById(R.id.civ_about)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_login_action -> {
                Toast.makeText(activityContext, "登录注册", Toast.LENGTH_SHORT).show()
            }
            R.id.civ_favorite -> {
                Toast.makeText(activityContext, "收藏", Toast.LENGTH_SHORT).show()
            }
            R.id.civ_hot_search -> {
                Toast.makeText(activityContext, "热搜", Toast.LENGTH_SHORT).show()
            }
            R.id.civ_web_address -> {
                startActivity(Intent(activityContext, CommonWebAddressActivity::class.java))
            }
            R.id.civ_so_hot -> {
                val intent = Intent(activityContext, BrowserActivity::class.java)
                intent.putExtra(Constant.BUNDLE_KEY_4_WEB_TITLE, "安卓爬网页")
                intent.putExtra(Constant.BUNDLE_KEY_4_WEB_URL, "http://blog.csdn.net/givemeacondom/article/details/50526518")
                startActivity(intent)
            }
            R.id.civ_about -> {
                Toast.makeText(activityContext, "关于", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
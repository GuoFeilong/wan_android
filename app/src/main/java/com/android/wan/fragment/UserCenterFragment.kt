package com.android.wan.fragment

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.android.wan.R
import com.android.wan.activity.BrowserActivity
import com.android.wan.activity.CommonWebAddressActivity
import com.android.wan.activity.HotKeyActivity
import com.android.wan.activity.LoginAndSignUpActivity
import com.android.wan.base.AbstractFragment
import com.android.wan.constant.Constant
import com.android.wan.customwidget.UserCenterItemView
import com.android.wan.net.response.LoginResponse
import com.android.wan.utils.SharedPreferencesUtil
import com.google.gson.Gson

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
    var loginContainer: LinearLayout? = null
    var loginUserName: TextView? = null
    var logout: TextView? = null

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
        logout?.setOnClickListener(this)

        if (SharedPreferencesUtil.login(activityContext!!)) {
            showLogin()
        } else {
            showSignUp()
        }
    }

    override fun initView(rootView: View) {
        loginOrRegist = rootView.findViewById(R.id.tv_login_action)
        favorite = rootView.findViewById(R.id.civ_favorite)
        hotSearch = rootView.findViewById(R.id.civ_hot_search)
        commonWebSite = rootView.findViewById(R.id.civ_web_address)
        jsonup = rootView.findViewById(R.id.civ_so_hot)
        about = rootView.findViewById(R.id.civ_about)
        loginContainer = rootView.findViewById(R.id.ll_login_container)
        loginUserName = rootView.findViewById(R.id.tv_user_name)
        logout = rootView.findViewById(R.id.tv_logout)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_login_action -> {
                val intent = Intent(activityContext, LoginAndSignUpActivity::class.java)
                startActivityForResult(intent, Constant.REQUEST_CODE_4_LOGIN)
            }
            R.id.civ_favorite -> {
                Toast.makeText(activityContext, "收藏", Toast.LENGTH_SHORT).show()
            }
            R.id.civ_hot_search -> {
                startActivity(Intent(activityContext, HotKeyActivity::class.java))
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
            R.id.tv_logout -> {
                SharedPreferencesUtil.saveShareData(activityContext!!,"",Constant.LOGIN_SUCCESS_KEY)
                showSignUp()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            Constant.REQUEST_CODE_4_LOGIN -> {
                if (resultCode == Activity.RESULT_OK) {
                    showLogin()
                }
            }
        }

    }

    fun showLogin() {
        loginContainer?.visibility = View.VISIBLE
        loginOrRegist?.visibility = View.GONE
        val readShareData = SharedPreferencesUtil.readShareData(activityContext!!, Constant.LOGIN_SUCCESS_KEY)
        val loginResponse = Gson().fromJson(readShareData, LoginResponse::class.java)
        loginUserName?.setText(loginResponse.data.username)
        logout?.visibility = View.VISIBLE
    }

    fun showSignUp() {
        loginContainer?.visibility = View.GONE
        loginOrRegist?.visibility = View.VISIBLE
        logout?.visibility = View.GONE
    }
}
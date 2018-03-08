package com.android.wan.utils

import android.content.Context
import com.android.wan.constant.Constant
import com.android.wan.net.response.LoginResponse
import com.google.gson.Gson

/**
 * @author by 有人@我 on 18/3/8.
 */
object SharedPreferencesUtil {
    fun saveShareData(context: Context, data: String, key: String) {
        val sharedPreferences = context.getSharedPreferences(null, Context.MODE_PRIVATE)
        val edit = sharedPreferences.edit()
        edit.putString(key, data)
        edit.apply()
    }

    fun readShareData(context: Context, key: String): String {
        val sharedPreferences = context.getSharedPreferences(null, Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, "")
    }

    fun login(context: Context): Boolean {
        val readShareData = SharedPreferencesUtil.readShareData(context, Constant.LOGIN_SUCCESS_KEY)
        val loginResponse = Gson().fromJson(readShareData, LoginResponse::class.java)
        return loginResponse !== null
    }
}
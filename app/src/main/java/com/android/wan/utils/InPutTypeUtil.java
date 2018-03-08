package com.android.wan.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * @author by 有人@我 on 18/3/8.
 */

public class InPutTypeUtil {
    /**
     * 隐藏软键盘
     *
     * @param context 上下文
     */
    public static void hidenputKeyboardImmediately(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}

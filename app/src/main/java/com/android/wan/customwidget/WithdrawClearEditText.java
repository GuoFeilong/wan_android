package com.android.wan.customwidget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import com.android.wan.R;

/**
 * @author by 有人@我 on 18/3/6.
 */

public class WithdrawClearEditText extends AppCompatEditText implements View.OnFocusChangeListener, TextWatcher, ActionMode.Callback {
    private Drawable mClearDrawable;
    private OnWithdrawFocusChangedListener withdrawFocusChangedListener;

    public WithdrawClearEditText(Context context) {
        this(context, null);
    }

    public WithdrawClearEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public WithdrawClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @SuppressLint("NewApi")
    private void init() {
        mClearDrawable = getCompoundDrawables()[2];
        if (mClearDrawable == null) {
            mClearDrawable = getResources().getDrawable(R.mipmap.city_keyword_select_closeicon);
        }

        mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());
        setClearIconVisible(false);
        setOnFocusChangeListener(this);
        addTextChangedListener(this);
        // 禁止粘贴
        setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI);
        setLongClickable(false);
        setCustomSelectionActionModeCallback(this);
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {
                boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight()) && (event.getX() < ((getWidth() - getPaddingRight())));
                if (touchable) {
                    this.setText("");
                }
            }
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (withdrawFocusChangedListener != null) {
            withdrawFocusChangedListener.withdrawFocus(hasFocus);
        }
        if (!hasFocus) {
            setClearIconVisible(false);
        }
        if (hasFocus && !TextUtils.isEmpty(getText().toString())) {
            setClearIconVisible(true);
        }
    }

    public void setClearIconVisible(boolean visible) {
        Drawable right = visible ? mClearDrawable : null;
        setCompoundDrawablePadding(12);
        setCompoundDrawablesWithIntrinsicBounds(getCompoundDrawables()[0], getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int count, int after) {


    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        setClearIconVisible(s.toString().length() > 0);
    }

    @Override
    public boolean onActionItemClicked(ActionMode arg0, MenuItem arg1) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean onCreateActionMode(ActionMode arg0, Menu arg1) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean onPrepareActionMode(ActionMode arg0, Menu arg1) {
        // TODO Auto-generated method stub
        return false;
    }

    public interface OnWithdrawFocusChangedListener {
        void withdrawFocus(boolean hasFocus);
    }

    public void setWithdrawFocusChangedListener(OnWithdrawFocusChangedListener withdrawFocusChangedListener) {
        this.withdrawFocusChangedListener = withdrawFocusChangedListener;
    }
}

package com.android.wan.customwidget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author by 有人@我 on 18/1/26.
 */

@SuppressLint("AppCompatCustomView")
public class MarqueeTextView extends TextView {
    public MarqueeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MarqueeTextView(Context context) {
        super(context);
    }

    @Override
    public boolean isFocused() {
        return true;
    }

}

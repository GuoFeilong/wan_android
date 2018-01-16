package com.android.wan.customwidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.wan.R;

import java.util.List;

/**
 * @author by 有人@我 on 18/1/16.
 */

public class CarLayout extends RelativeLayout {
    private int carIconRes;
    private int carTextColor;
    private int carTextSize;
    private List<String> carsData;

    public CarLayout(Context context) {
        this(context, null);
    }

    public CarLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CarLayout, defStyleAttr, R.style.DefCarStyle);
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.CarLayout_carIcon:
                    carIconRes = typedArray.getResourceId(attr, 0);
                    break;
                case R.styleable.CarLayout_carTextColor:
                    carTextColor = typedArray.getColor(attr, Color.RED);
                    break;
                case R.styleable.CarLayout_carTextSize:
                    carTextSize = typedArray.getDimensionPixelSize(attr, 0);
                    break;
                default:
                    break;

            }
        }

        typedArray.recycle();
    }

    public void setCarsData(List<String> carsData) {
        this.carsData = carsData;

    }

}

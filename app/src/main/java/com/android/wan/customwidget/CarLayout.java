package com.android.wan.customwidget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.wan.R;
import com.android.wan.callback.OnCarClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @author by 有人@我 on 18/1/16.
 */

public class CarLayout extends RelativeLayout {
    private Drawable carIconRes;
    private int carTextColor;
    private int carTextSize;
    private int carTopMargin;
    private List<String> carsData;
    private List<TextView> allCars;
    private int carIndex;
    private boolean carRun;
    private OnCarClickListener carClickListener;

    public CarLayout(Context context) {
        this(context, null);
    }

    public CarLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        allCars = new ArrayList<>();
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CarLayout, defStyleAttr, R.style.DefCarStyle);
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.CarLayout_carIcon:
                    carIconRes = typedArray.getDrawable(attr);
                    break;
                case R.styleable.CarLayout_carTextColor:
                    carTextColor = typedArray.getColor(attr, Color.RED);
                    break;
                case R.styleable.CarLayout_carTextSize:
                    carTextSize = typedArray.getInt(attr, 0);
                    break;
                case R.styleable.CarLayout_carTopMargin:
                    carTopMargin = typedArray.getDimensionPixelOffset(attr, 0);
                    break;
                default:
                    break;

            }
        }

        typedArray.recycle();
    }

    public void setCarsData(List<String> carsData) {
        this.carsData = carsData;
        if (carsData != null) {
            if (carsData.size() == 3) {
                makeThreeCars();
            } else {
                makeCars();
            }
        }
    }

    private void makeCars() {
        for (int i = 0; i < carsData.size(); i++) {
            TextView car = new TextView(getContext());
            car.setTextColor(carTextColor);
            car.setTextSize(carTextSize);
            car.setId(i);
            car.setText(carsData.get(i));
            car.setGravity(Gravity.CENTER);
            carIconRes.setBounds(0, 0, carIconRes.getMinimumWidth(), carIconRes.getMinimumHeight());
            car.setCompoundDrawables(carIconRes, null, null, null);
            car.setCompoundDrawablePadding(16);
            RelativeLayout.LayoutParams carParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            carParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            if (i > 0) {
                carParams.addRule(RelativeLayout.BELOW, i - 1);
                carParams.topMargin = carTopMargin;
                carParams.topMargin = 32;
            }
            car.setLayoutParams(carParams);
            allCars.add(car);
            addView(car);

            final int finalI = i;
            car.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (carClickListener != null) {
                        carClickListener.carClick(finalI);
                    }
                }
            });
        }
    }

    private void makeThreeCars() {
        TextView car = new TextView(getContext());
        car.setTextColor(carTextColor);
        car.setTextSize(carTextSize);
        car.setText(carsData.get(0));
        car.setGravity(Gravity.CENTER);
        carIconRes.setBounds(0, 0, carIconRes.getMinimumWidth(), carIconRes.getMinimumHeight());
        car.setCompoundDrawables(carIconRes, null, null, null);
        car.setCompoundDrawablePadding(16);
        RelativeLayout.LayoutParams carParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        carParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        carParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        carParams.topMargin = 32;
        car.setLayoutParams(carParams);
        allCars.add(car);
        addView(car);

        car.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (carClickListener != null) {
                    carClickListener.carClick(0);
                }
            }
        });

        car = new TextView(getContext());
        car.setTextColor(carTextColor);
        car.setTextSize(carTextSize);
        car.setText(carsData.get(1));
        car.setGravity(Gravity.CENTER);
        carIconRes.setBounds(0, 0, carIconRes.getMinimumWidth(), carIconRes.getMinimumHeight());
        car.setCompoundDrawables(carIconRes, null, null, null);
        car.setCompoundDrawablePadding(16);
        carParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        carParams.addRule(RelativeLayout.CENTER_VERTICAL);
        carParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        car.setLayoutParams(carParams);
        allCars.add(car);
        addView(car);

        car.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (carClickListener != null) {
                    carClickListener.carClick(1);
                }
            }
        });


        car = new TextView(getContext());
        car.setTextColor(carTextColor);
        car.setTextSize(TypedValue.COMPLEX_UNIT_SP, carTextSize);
        car.setText(carsData.get(2));
        car.setGravity(Gravity.CENTER);
        carIconRes.setBounds(0, 0, carIconRes.getMinimumWidth(), carIconRes.getMinimumHeight());
        car.setCompoundDrawables(carIconRes, null, null, null);
        car.setCompoundDrawablePadding(16);
        carParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        carParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        carParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        car.setLayoutParams(carParams);
        allCars.add(car);
        addView(car);

        car.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (carClickListener != null) {
                    carClickListener.carClick(2);
                }
            }
        });

    }

    public void openCar() {
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        if (carIndex < allCars.size()) {
                            carRun(allCars.get(carIndex));
                            carIndex++;
                        }
                    }
                });
    }

    private void carRun(TextView textView) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(textView, "translationX", textView.getX(), getWidth());
        objectAnimator.setDuration(2000);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.start();
        carRun = true;
    }

    public boolean getCarRunState() {
        return carRun;
    }

    public void setCarClickListener(OnCarClickListener carClickListener) {
        this.carClickListener = carClickListener;
    }
}

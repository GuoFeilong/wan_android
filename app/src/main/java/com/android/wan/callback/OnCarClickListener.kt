package com.android.wan.callback

import com.android.wan.net.response.entity.CarData

/**
 * @author by 有人@我 on 18/1/17.
 */
interface OnCarClickListener {
    /**
     * 车的点击事件
     */
    fun carClick(carData: CarData)
}
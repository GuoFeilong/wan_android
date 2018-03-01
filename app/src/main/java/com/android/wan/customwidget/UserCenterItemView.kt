package com.android.wan.customwidget

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.android.wan.R

/**
 * @author by 有人@我 on 18/3/1.
 */

class UserCenterItemView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var itemIcon: ImageView? = null
    private var itemName: TextView? = null
    private var itemDesc: TextView? = null

    init {
        val rootView = LayoutInflater.from(context).inflate(R.layout.include_user_center_item, this, true)
        initView(rootView)
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.UserCenterItem, defStyleAttr, 0)
        val indexCount = typedArray.indexCount
        for (i in 0 until indexCount) {
            val attr = typedArray.getIndex(i)
            when (attr) {
                R.styleable.UserCenterItem_itemIcon -> if (itemIcon != null) {
                    itemIcon!!.setImageResource(typedArray.getResourceId(attr, 0))
                }
                R.styleable.UserCenterItem_itemName -> if (itemName != null) {
                    itemName!!.text = typedArray.getString(attr)
                }
                R.styleable.UserCenterItem_itemDesc -> if (itemDesc != null) {
                    itemDesc!!.text = typedArray.getString(attr)
                }
                else -> {
                }
            }
        }
        typedArray.recycle()
    }

    private fun initView(rootView: View) {
        try {
            itemIcon = rootView.findViewById(R.id.iv_user_center_item)
            itemName = rootView.findViewById(R.id.tv_user_center_item_name)
            itemDesc = rootView.findViewById(R.id.tv_user_center_right_arrow)
        } catch (e: Exception) {
            Log.e(TAG, "initView: " + e.toString())
        }

    }

    fun setItemIcon(resId: Int) {
        if (itemIcon != null) {
            itemIcon!!.setImageResource(resId)
        }
    }

    fun setItemName(name: String) {
        if (itemName != null) {
            itemName!!.text = name
        }
    }

    fun setItemDesc(desc: String) {
        if (itemDesc != null) {
            itemDesc!!.text = desc
        }
    }

    companion object {
        private val TAG = "UserCenterItemView"
    }
}

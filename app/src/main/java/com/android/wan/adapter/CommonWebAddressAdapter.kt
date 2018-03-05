package com.android.wan.adapter

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.android.wan.R
import com.android.wan.callback.OnRecyItemClickListener
import com.android.wan.customwidget.MarqueeTextView
import com.android.wan.net.response.FriendListResponse
import java.util.*

/**
 * @author by 有人@我 on 18/3/5.
 */
class CommonWebAddressAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var webAddressData: List<FriendListResponse.Data>? = ArrayList<FriendListResponse.Data>()
    var itemClickListener: OnRecyItemClickListener<FriendListResponse.Data>? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return WebAddressHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_common_web_address, parent, false))
    }

    override fun getItemCount(): Int {
        return webAddressData!!.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val currentData = webAddressData?.get(position)
        when (holder) {
            is WebAddressHolder -> {
                holder.webAddress.setText(currentData?.link)
                holder.webAuthName.setText(currentData?.name)
            }
        }
        holder!!.itemView.setOnClickListener {
            itemClickListener?.onRecyItemClick(position, currentData!!)
        }
    }


    class WebAddressHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var webAuthName: TextView = itemView.findViewById(R.id.authName)
        var webAddress: MarqueeTextView = itemView.findViewById(R.id.ariticleTitle)
    }
}
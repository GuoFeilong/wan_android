package com.android.wan.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.afollestad.aesthetic.AestheticImageView
import com.afollestad.aesthetic.AestheticTextView
import com.android.wan.R
import com.android.wan.callback.OnRecyItemClickListener
import com.android.wan.net.response.BannerResponse
import com.bumptech.glide.Glide

/**
 * @author by 有人@我 on 2018/1/14.
 */
class BannerAdapter(mContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mContext: Context? = mContext
    var bannerData: List<BannerResponse.Data>? = ArrayList<BannerResponse.Data>()
    var itemClickListener: OnRecyItemClickListener<BannerResponse.Data>? = null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val currentBannerData: BannerResponse.Data = bannerData!!.get(position)
        when (holder) {
            is BannerHolder -> {
                holder.bannerDesc.text = currentBannerData.title
                Glide.with(mContext!!)
                        .load(currentBannerData.imagePath)
                        .into(holder.banner)
            }
        }
        holder!!.itemView.setOnClickListener {
            itemClickListener?.onRecyItemClick(position, currentBannerData)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return BannerHolder(LayoutInflater.from(mContext).inflate(R.layout.item_banner, parent, false))
    }

    override fun getItemCount(): Int {
        return bannerData!!.size
    }


    class BannerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var banner: ImageView = itemView.findViewById(R.id.bannerItem)
        var bannerDesc: TextView = itemView.findViewById(R.id.bannerText)
    }
}
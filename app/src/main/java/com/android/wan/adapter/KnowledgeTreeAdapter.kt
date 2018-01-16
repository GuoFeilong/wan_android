package com.android.wan.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.android.wan.R
import com.android.wan.callback.OnKnowlegeTreeClickListener
import com.android.wan.customwidget.FlowLayout
import com.android.wan.net.response.KnowledgeHierarchyResponse
import java.util.*

/**
 * @author by 有人@我 on 18/1/16.
 */
class KnowledgeTreeAdapter(private var mContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var knowledgeTrees: List<KnowledgeHierarchyResponse.Data>? = ArrayList<KnowledgeHierarchyResponse.Data>()
    var knowlegetTreeClickListener: OnKnowlegeTreeClickListener<KnowledgeHierarchyResponse.Data>? = null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val knowledgeData: KnowledgeHierarchyResponse.Data = knowledgeTrees!!.get(position)
        when (holder) {
            is KnowledgeViewHolder -> {
                val knowlegeFlowTitles: List<String> = ArrayList<String>()
                val childrens: List<KnowledgeHierarchyResponse.Data.Children> = knowledgeData.children!!

                for (temp: KnowledgeHierarchyResponse.Data.Children in childrens) {
                    (knowlegeFlowTitles as ArrayList).add(temp.name)
                }

                holder.knowledgeTitle.text = knowledgeData.name
                holder.knowledgeFlow.removeAllViews()
                holder.knowledgeFlow.addViewText(knowlegeFlowTitles)

                holder.knowledgeFlow.setFlowChildClickListener(object : FlowLayout.OnFlowChildClickListener {
                    override fun onChildClick(position: Int) {
                        knowlegetTreeClickListener?.onKnowlegeTreeLeafClick(childrens.get(position))
                    }
                })
            }
        }

        holder!!.itemView.setOnClickListener {
            knowlegetTreeClickListener?.onRecyItemClick(position, knowledgeData)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return KnowledgeViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_knowdedge_tree, parent, false))
    }

    override fun getItemCount(): Int {
        return knowledgeTrees!!.size
    }


    class KnowledgeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var knowledgeTitle: TextView = itemView.findViewById(R.id.knowledgeTitle)
        var knowledgeFlow: FlowLayout = itemView.findViewById(R.id.flowKnowledge)
    }
}
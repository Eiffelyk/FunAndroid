package com.eiffelyk.lib_base.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecycleViewAdapter<T, VH : RecyclerView.ViewHolder>(var context: Context) :
    RecyclerView.Adapter<VH>() {
    var dataList: MutableList<T> = mutableListOf()
    var mOnItemClickListener: OnItemClickListener<T>? = null
    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.setOnClickListener {
            mOnItemClickListener?.onItemClick(dataList[position], position)
        }
    }

    fun setData(source: MutableList<T>) {
        dataList = source
        notifyDataSetChanged()
    }

    interface OnItemClickListener<in T> {
        fun onItemClick(item: T, position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener<T>) {
        this.mOnItemClickListener = listener
    }
}
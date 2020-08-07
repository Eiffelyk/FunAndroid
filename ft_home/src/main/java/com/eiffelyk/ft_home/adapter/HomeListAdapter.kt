package com.eiffelyk.ft_home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eiffelyk.ft_home.databinding.HomeListItemBinding
import com.eiffelyk.lib_base.model.DataBean
import com.eiffelyk.lib_base.service.webview.warp.WebViewWarpService

class HomeListAdapter(context: Context) :
    PagedListAdapter<DataBean, HomeListAdapter.ViewHolder>(object :
        DiffUtil.ItemCallback<DataBean>() {
        override fun areItemsTheSame(oldItem: DataBean, newItem: DataBean): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DataBean, newItem: DataBean): Boolean {
            return oldItem.id == newItem.id
        }

    }) {
    private var mContext = context
    private var inflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeListAdapter.ViewHolder {
        val binding = HomeListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding.root, binding, mContext)
    }

    override fun onBindViewHolder(holder: HomeListAdapter.ViewHolder, position: Int) {
        holder.setData(getItem(position)!!)
    }

    class ViewHolder(item: View, binding: HomeListItemBinding, context: Context) :
        RecyclerView.ViewHolder(item) {
        private var mBinding = binding
        private val mContext = context
        fun setData(dataBean: DataBean) {
            mBinding.item = dataBean
            mBinding.homeListItemParent.setOnClickListener {
                WebViewWarpService.instance.start(mContext, dataBean.title, dataBean.link)
            }
        }
    }
}
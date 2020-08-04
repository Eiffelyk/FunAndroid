package com.eiffelyk.ft_tree_detail.adapter


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eiffelyk.ft_tree_detail.R
import com.eiffelyk.ft_tree_detail.databinding.ItemTreeDetailBinding
import com.eiffelyk.lib_base.model.DataBean
import com.eiffelyk.lib_base.service.webview.warp.WebViewWarpService

class TreeDetailAdapter(context: Context) :
    PagedListAdapter<DataBean, TreeDetailAdapter.ViewHolder>(
        object : DiffUtil.ItemCallback<DataBean>() {
            override fun areItemsTheSame(oldItem: DataBean, newItem: DataBean): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataBean, newItem: DataBean): Boolean {
                return oldItem.id == newItem.id
            }

        }
    ) {
    private val mContext = context
    private val inflater = LayoutInflater.from(mContext)

    inner class ViewHolder(item: View, binding: ItemTreeDetailBinding) :
        RecyclerView.ViewHolder(item) {
        private val mBinding = binding
        fun setData(item: DataBean): Unit {
            mBinding.item = item
            mBinding.itemTreeDetailParent.setOnClickListener {
                Log.e("馋猫", item.link)
                WebViewWarpService.instance.start(mContext, item.title, item.link)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            DataBindingUtil.inflate<ItemTreeDetailBinding>(
                inflater,
                R.layout.item_tree_detail,
                parent,
                false
            )
        return ViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(getItem(position)!!)
    }
}
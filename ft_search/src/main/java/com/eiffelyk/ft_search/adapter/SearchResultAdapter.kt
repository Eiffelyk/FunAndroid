package com.eiffelyk.ft_search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eiffelyk.ft_search.R
import com.eiffelyk.ft_search.databinding.SearchResultItemBinding
import com.eiffelyk.lib_base.model.DataBean

class SearchResultAdapter(context: Context) :
    PagedListAdapter<DataBean, SearchResultAdapter.ViewHolder>(object :
        DiffUtil.ItemCallback<DataBean>() {
        override fun areItemsTheSame(oldItem: DataBean, newItem: DataBean): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataBean, newItem: DataBean): Boolean {
            return oldItem == newItem
        }

    }) {
    class ViewHolder(itemView: View, binding: SearchResultItemBinding) :
        RecyclerView.ViewHolder(itemView) {
        private val mBinding = binding
        fun setData(item: DataBean?): Unit {
            mBinding.data = item
        }
    }

    private val inflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<SearchResultItemBinding>(
            inflater,
            R.layout.search_result_item,
            parent,
            false
        )
        return ViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(getItem(position))
    }
}
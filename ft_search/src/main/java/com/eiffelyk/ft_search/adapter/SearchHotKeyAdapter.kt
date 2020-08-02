package com.eiffelyk.ft_search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.eiffelyk.ft_search.R
import com.eiffelyk.ft_search.databinding.HotkeyItemBinding
import com.eiffelyk.ft_search.model.HotKeyModel
import com.eiffelyk.lib_base.adapter.BaseRecycleViewAdapter

class SearchHotKeyAdapter(context: Context) :
    BaseRecycleViewAdapter<HotKeyModel, SearchHotKeyAdapter.ViewHolder>(context) {
    class ViewHolder(itemView: View, binding: HotkeyItemBinding) :
        RecyclerView.ViewHolder(itemView) {
        private val mBinding = binding
        fun setData(hotModel: HotKeyModel): Unit {
            mBinding.hotPageItemText.text = hotModel.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<HotkeyItemBinding>(
            LayoutInflater.from(context),
            R.layout.hotkey_item,
            parent,
            false
        )
        return ViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.setData(dataList[position])
    }
}
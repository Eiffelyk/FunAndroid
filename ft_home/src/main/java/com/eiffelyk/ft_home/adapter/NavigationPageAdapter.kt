package com.eiffelyk.ft_home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eiffelyk.ft_home.databinding.FragmentNavPagerItemBinding
import com.eiffelyk.ft_home.model.nav.NavigationItemDetail
import com.eiffelyk.lib_base.adapter.BaseRecycleViewAdapter

class NavigationPageAdapter(context: Context) :
    BaseRecycleViewAdapter<NavigationItemDetail, NavigationPageAdapter.ViewHolder>(context) {
    class ViewHolder(item: View, bing: FragmentNavPagerItemBinding) :
        RecyclerView.ViewHolder(item) {
        private val mBinding = bing
        fun setItemData(data: NavigationItemDetail): Unit {
            mBinding.fragmentNavPaperItem.text = data.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            FragmentNavPagerItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.setItemData(dataList[position])
    }
}
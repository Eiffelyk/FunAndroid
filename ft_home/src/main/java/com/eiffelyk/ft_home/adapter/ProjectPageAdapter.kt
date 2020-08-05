package com.eiffelyk.ft_home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eiffelyk.ft_home.databinding.FragmentProjectPageItemBinding
import com.eiffelyk.ft_home.databinding.FragmentTreeItemBinding
import com.eiffelyk.ft_home.model.project.ProjectItemSub
import com.eiffelyk.lib_image_loader.ImageLoaderManager

class ProjectPageAdapter constructor(context: Context) :
    PagedListAdapter<ProjectItemSub, ProjectPageAdapter.ViewHolder>(
        object : DiffUtil.ItemCallback<ProjectItemSub>() {
            override fun areItemsTheSame(
                oldItem: ProjectItemSub,
                newItem: ProjectItemSub
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ProjectItemSub,
                newItem: ProjectItemSub
            ): Boolean {
                return oldItem.id == newItem.id
            }

        }
    ) {
    private val mContext = context
    private val mInflater = LayoutInflater.from(mContext)

    class ViewHolder(itemView: View, binding: FragmentProjectPageItemBinding, context: Context) :
        RecyclerView.ViewHolder(itemView) {
        private val mBinding = binding
        private val mContext = context
        fun setData(item: ProjectItemSub?) {
            mBinding.itemData = item
            mBinding.context = mContext
            ImageLoaderManager.instance.displayImageForView(mBinding.pic, item!!.envelopePic)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentProjectPageItemBinding.inflate(mInflater, parent, false)
        return ViewHolder(binding.root, binding, mContext)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.setData(item)
    }

}
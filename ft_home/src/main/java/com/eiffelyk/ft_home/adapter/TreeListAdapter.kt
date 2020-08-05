package com.eiffelyk.ft_home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.eiffelyk.ft_home.R
import com.eiffelyk.ft_home.databinding.FragmentTreeItemBinding
import com.eiffelyk.ft_home.model.tree.TreeData
import com.eiffelyk.ft_home.model.tree.TreeDataItem
import com.eiffelyk.lib_base.service.treedetail.wrap.TreeDetailServiceImplWarp
import com.win.lib_common_ui.flowlayout.adapter.TagAdapter

class TreeListAdapter(context: Context, data: MutableList<TreeData>) :
    RecyclerView.Adapter<TreeListAdapter.ViewHolder>() {

    private val mContext = context
    private var mData = data
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<FragmentTreeItemBinding>(
            LayoutInflater.from(
                mContext
            ), R.layout.fragment_tree_item, parent, false
        )
        return ViewHolder(binding.root, binding)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(mData[position], mContext)
    }

    class ViewHolder(itemVIew: View, binding: FragmentTreeItemBinding) :
        RecyclerView.ViewHolder(itemVIew) {
        private val mBinding = binding
        fun setData(treeData: TreeData, context: Context): Unit {
            mBinding.treeData = treeData
            val tagChildren = treeData.children
            mBinding.mFlowLayout.setAdapter(object : TagAdapter() {
                override fun getItemCount(): Int {
                    return tagChildren.size
                }

                override fun createView(
                    inflater: LayoutInflater,
                    parent: ViewGroup,
                    position: Int
                ): View {
                    return inflater.inflate(R.layout.flow_layout_item, parent, false)
                }

                override fun bindView(view: View, position: Int) {
                    (view as TextView).text = tagChildren[position].name
                }

                override fun onItemViewClick(view: View, position: Int) {
                    val item = tagChildren[position]
                    TreeDetailServiceImplWarp.start(context, item.id, item.name)
                }
            })
        }
    }
}
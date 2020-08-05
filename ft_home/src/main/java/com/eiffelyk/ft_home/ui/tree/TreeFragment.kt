package com.eiffelyk.ft_home.ui.tree

import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eiffelyk.ft_home.R
import com.eiffelyk.ft_home.adapter.TreeListAdapter
import com.eiffelyk.ft_home.databinding.FragmentTreeBinding
import com.eiffelyk.ft_home.model.tree.TreeData
import com.eiffelyk.lib_base.base.BaseFragment

class TreeFragment : BaseFragment<TreeViewModel, FragmentTreeBinding>() {
    private lateinit var adapter: TreeListAdapter
    private lateinit var mRecyclerView: RecyclerView
    override fun getLayoutResId(): Int = R.layout.fragment_tree

    override fun initData() {
        mViewModel.getTreeList()
        mViewModel.getTreeDataLive().observe(this, Observer<MutableList<TreeData>> {
            adapter = TreeListAdapter(requireContext(), it)
            mRecyclerView.adapter = adapter
        })
    }

    override fun iniView() {
        mRecyclerView = mViewDataBinding.fragmentTreeRecycler
        mRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        decoration.setDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.home_list_divider
            )!!
        )
        mRecyclerView.addItemDecoration(decoration)
    }


}
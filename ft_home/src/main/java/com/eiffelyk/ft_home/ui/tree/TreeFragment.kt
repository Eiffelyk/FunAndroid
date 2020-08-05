package com.eiffelyk.ft_home.ui.tree

import com.eiffelyk.ft_home.R
import com.eiffelyk.ft_home.databinding.FragmentTreeBinding
import com.eiffelyk.lib_base.base.BaseFragment

class TreeFragment : BaseFragment<TreeViewModel, FragmentTreeBinding>() {

    private lateinit var treeViewModel: TreeViewModel
    override fun getLayoutResId(): Int = R.layout.fragment_tree

    override fun initData() {
    }

    override fun iniView() {
    }


}
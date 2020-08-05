package com.eiffelyk.ft_home.ui.navi

import com.eiffelyk.ft_home.R
import com.eiffelyk.ft_home.databinding.FragmentNavBinding
import com.eiffelyk.lib_base.base.BaseFragment

class NaviFragment : BaseFragment<NaviViewModel,FragmentNavBinding>() {

    private lateinit var naviViewModel: NaviViewModel
    override fun getLayoutResId(): Int = R.layout.fragment_nav

    override fun initData() {
    }

    override fun iniView() {
    }

}
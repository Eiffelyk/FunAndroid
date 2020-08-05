package com.eiffelyk.ft_home.ui.mine

import com.eiffelyk.ft_home.R
import com.eiffelyk.ft_home.databinding.FragmentMineBinding
import com.eiffelyk.lib_base.base.BaseFragment

class MineFragment : BaseFragment<MineViewModel, FragmentMineBinding>() {

    private lateinit var mineViewModel: MineViewModel
    override fun getLayoutResId(): Int = R.layout.fragment_mine

    override fun initData() {
    }

    override fun iniView() {
    }

}
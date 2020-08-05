package com.eiffelyk.ft_home.ui.home

import com.eiffelyk.ft_home.R
import com.eiffelyk.ft_home.databinding.FragmentHomeBinding
import com.eiffelyk.lib_base.base.BaseFragment

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

  private lateinit var homeViewModel: HomeViewModel
  override fun getLayoutResId(): Int = R.layout.fragment_home

  override fun initData() {
  }

  override fun iniView() {
  }

}
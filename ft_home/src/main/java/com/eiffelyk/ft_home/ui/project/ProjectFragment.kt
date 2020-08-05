package com.eiffelyk.ft_home.ui.project


import com.eiffelyk.ft_home.R
import com.eiffelyk.ft_home.databinding.FragmentProjectBinding
import com.eiffelyk.lib_base.base.BaseFragment

class ProjectFragment : BaseFragment<ProjectViewModel,FragmentProjectBinding>() {

    private lateinit var projectViewModel: ProjectViewModel
    override fun getLayoutResId(): Int = R.layout.fragment_project

    override fun initData() {
    }

    override fun iniView() {
    }

}
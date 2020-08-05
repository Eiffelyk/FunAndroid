package com.eiffelyk.ft_home.di

import com.eiffelyk.ft_home.ui.home.HomeViewModel
import com.eiffelyk.ft_home.ui.main.MainViewModel
import com.eiffelyk.ft_home.ui.mine.MineViewModel
import com.eiffelyk.ft_home.ui.navi.NaviViewModel
import com.eiffelyk.ft_home.ui.project.ProjectViewModel
import com.eiffelyk.ft_home.ui.tree.TreeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var treeViewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { HomeViewModel() }
    viewModel { ProjectViewModel() }
    viewModel { NaviViewModel() }
    viewModel { TreeViewModel() }
    viewModel { MineViewModel() }
}
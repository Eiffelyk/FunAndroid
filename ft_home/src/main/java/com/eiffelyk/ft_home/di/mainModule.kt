package com.eiffelyk.ft_home.di

import com.eiffelyk.ft_home.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var treeViewModelModule = module {
    viewModel { MainViewModel() }
}
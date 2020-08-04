package com.eiffelyk.ft_tree_detail.di

import com.eiffelyk.ft_tree_detail.ui.TreeDetailRepository
import com.eiffelyk.ft_tree_detail.ui.TreeDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val treeDetailModule = module {
    factory {
        TreeDetailRepository(get())
    }
}

val treeDetailModelView = module {
    viewModel {
        TreeDetailViewModel(get())
    }
}
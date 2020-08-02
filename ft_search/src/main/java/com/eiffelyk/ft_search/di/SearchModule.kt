package com.eiffelyk.ft_search.di

import com.eiffelyk.ft_search.ui.repository.SearchRepository
import com.eiffelyk.ft_search.ui.viewmodel.HotKeyViewModel
import com.eiffelyk.ft_search.ui.viewmodel.SearchResultViewModel
import com.eiffelyk.ft_search.ui.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchRepositoryModule = module {
    single {
        SearchRepository(get())
    }
}

val searchViewModule = module {
    viewModel {
        SearchViewModel(get())
    }
    viewModel {
        SearchResultViewModel(get())
    }
    viewModel {
        HotKeyViewModel(get())
    }
}
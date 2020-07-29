package com.eiffelyk.ft_login.di

import com.eiffelyk.ft_login.ui.LoginRepository
import com.eiffelyk.ft_login.ui.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginRepoModule = module {
    single {
        LoginRepository(get())
    }
}

val loginViewModelModules = module {
    viewModel {
        LoginViewModel(get())
    }
}
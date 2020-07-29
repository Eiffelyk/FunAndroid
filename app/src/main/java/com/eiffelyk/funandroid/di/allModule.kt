package com.eiffelyk.funandroid.di

import com.eiffelyk.ft_home.di.treeViewModelModule
import com.eiffelyk.ft_login.di.loginRepoModule
import com.eiffelyk.ft_login.di.loginViewModelModules
import com.eiffelyk.lib_net.net.RetrofitClient
import com.google.gson.GsonBuilder
import org.koin.dsl.module

val otherModule = module {
    single {
        RetrofitClient.instance
    }

    single {
        GsonBuilder().disableHtmlEscaping().create()
    }
}
val allModule = listOf(
    otherModule,
    treeViewModelModule,
    loginRepoModule, loginViewModelModules
)
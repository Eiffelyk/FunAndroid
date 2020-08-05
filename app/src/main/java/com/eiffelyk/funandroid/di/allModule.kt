package com.eiffelyk.funandroid.di

import com.eiffelyk.ft_home.di.mainRepoModule
import com.eiffelyk.ft_home.di.mainViewModelModule
import com.eiffelyk.ft_login.di.loginRepoModule
import com.eiffelyk.ft_login.di.loginViewModelModules
import com.eiffelyk.ft_search.di.searchRepositoryModule
import com.eiffelyk.ft_search.di.searchViewModule
import com.eiffelyk.ft_tree_detail.di.treeDetailModelView
import com.eiffelyk.ft_tree_detail.di.treeDetailModule
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
    mainViewModelModule, mainRepoModule,
    loginRepoModule, loginViewModelModules,
    searchRepositoryModule, searchViewModule,
    treeDetailModule, treeDetailModelView
)
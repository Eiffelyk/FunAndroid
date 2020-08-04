package com.eiffelyk.lib_base.datasource

import android.annotation.SuppressLint
import androidx.arch.core.executor.ArchTaskExecutor
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList

class MutablePageKeyDtaSource<Value> : PageKeyedDataSource<Int, Value>() {
    val data = mutableListOf<Value>()
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Value>
    ) {
        callback.onResult(data, null, null)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Value>) {
        callback.onResult(emptyList(), null)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Value>) {
        callback.onResult(emptyList(), null)
    }

    @SuppressLint("RestrictedApi")
    fun buildNewPageList(config: PagedList.Config): PagedList<Value>? {
        return PagedList.Builder<Int, Value>(this, config)
            .setFetchExecutor(ArchTaskExecutor.getIOThreadExecutor())
            .setNotifyExecutor(ArchTaskExecutor.getMainThreadExecutor()).build()
    }
}
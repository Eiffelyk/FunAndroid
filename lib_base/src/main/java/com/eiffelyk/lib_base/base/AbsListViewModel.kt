package com.eiffelyk.lib_base.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

abstract class AbsListViewModel<T> : ViewModel() {

    private var dataSource: DataSource<Int, T>? = null
    private var pageData: LiveData<PagedList<T>>

    fun getData(): LiveData<PagedList<T>> {
        return pageData
    }

    fun getDataSource(): DataSource<Int, T>? {
        return dataSource
    }

    init {
        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(10)
            .setPageSize(10)
            .build()
        val factory = object : DataSource.Factory<Int, T>() {
            override fun create(): DataSource<Int, T> {
                if (dataSource == null || dataSource?.isInvalid != false) {
                    dataSource = createDataSource()
                }
                return dataSource!!
            }
        }
        pageData = LivePagedListBuilder<Int, T>(factory, config).build()
    }

    abstract fun createDataSource(): DataSource<Int, T>
}
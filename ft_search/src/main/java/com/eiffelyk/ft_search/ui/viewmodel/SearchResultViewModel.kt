package com.eiffelyk.ft_search.ui.viewmodel

import android.widget.Toast
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.eiffelyk.ft_search.ui.repository.SearchRepository
import com.eiffelyk.lib_base.base.AbsListViewModel
import com.eiffelyk.lib_base.model.DataBean
import com.eiffelyk.lib_base.utils.BaseContext
import com.eiffelyk.lib_net.model.NetResult
import kotlinx.coroutines.launch
import java.util.*

class SearchResultViewModel(private val searchRepository: SearchRepository) :
    AbsListViewModel<DataBean>() {
    private var mHotKey: String = ""
    fun setHotKey(hotKey: String): Unit {
        this.mHotKey = hotKey
    }

    fun getHotKey(): String {
        return mHotKey
    }

    override fun createDataSource(): DataSource<Int, DataBean> {
        return object : PageKeyedDataSource<Int, DataBean>() {
            override fun loadInitial(
                params: LoadInitialParams<Int>,
                callback: LoadInitialCallback<Int, DataBean>
            ) {
                loadData(0, callback)
            }

            override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, DataBean>) {
                loadMoreData(params.key, callback)
            }

            override fun loadBefore(
                params: LoadParams<Int>,
                callback: LoadCallback<Int, DataBean>
            ) {
                callback.onResult(Collections.emptyList(), null)
            }

        }
    }

    private fun loadData(i: Int, callback: PageKeyedDataSource.LoadInitialCallback<Int, DataBean>) {
        viewModelScope.launch {
            var result = searchRepository.search(i, getHotKey())
            if (result is NetResult.Success) {
                callback.onResult(result.data.datas, null, 1)
            } else if (result is NetResult.Error) {
                Toast.makeText(
                    BaseContext.instance.getContext(),
                    result.exception.errorMsg,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun loadMoreData(key: Int, callback: PageKeyedDataSource.LoadCallback<Int, DataBean>) {
        viewModelScope.launch {
            var result = searchRepository.search(key, getHotKey())
            if (result is NetResult.Success) {
                callback.onResult(result.data.datas, key + 1)
            } else if (result is NetResult.Error) {
                Toast.makeText(
                    BaseContext.instance.getContext(),
                    result.exception.errorMsg,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
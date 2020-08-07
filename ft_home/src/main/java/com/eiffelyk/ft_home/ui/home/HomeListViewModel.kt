package com.eiffelyk.ft_home.ui.home

import android.widget.Toast
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.eiffelyk.lib_base.base.AbsListViewModel
import com.eiffelyk.lib_base.model.DataBean
import com.eiffelyk.lib_base.utils.BaseContext
import com.eiffelyk.lib_net.model.NetResult
import kotlinx.coroutines.launch
import java.util.*

class HomeListViewModel(private val homeRepository: HomeRepository) : AbsListViewModel<DataBean>() {
    override fun createDataSource(): DataSource<Int, DataBean> {
        return object : PageKeyedDataSource<Int, DataBean>() {
            override fun loadInitial(
                params: LoadInitialParams<Int>,
                callback: LoadInitialCallback<Int, DataBean>
            ) {
                getHomeList(0, callback)
            }

            override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, DataBean>) {
                getHomeListMore(params.key, callback)
            }

            override fun loadBefore(
                params: LoadParams<Int>,
                callback: LoadCallback<Int, DataBean>
            ) {
                callback.onResult(Collections.emptyList(), null)
            }

        }
    }

    fun getHomeListMore(
        key: Int,
        callback: PageKeyedDataSource.LoadCallback<Int, DataBean>
    ) {
        viewModelScope.launch {
            val homeData = homeRepository.getHomeList(key)
            if (homeData is NetResult.Success) {
                callback.onResult(homeData.data.datas, key + 1)
            } else if (homeData is NetResult.Error) {
                Toast.makeText(
                    BaseContext.instance.getContext(),
                    homeData.exception.errorMsg,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun getHomeList(
        key: Int,
        callback: PageKeyedDataSource.LoadInitialCallback<Int, DataBean>
    ) {
        viewModelScope.launch {
            val homeData = homeRepository.getHomeList(key)
            if (homeData is NetResult.Success) {
                callback.onResult(homeData.data.datas,null, key + 1)
            } else if (homeData is NetResult.Error) {
                Toast.makeText(
                    BaseContext.instance.getContext(),
                    homeData.exception.errorMsg,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
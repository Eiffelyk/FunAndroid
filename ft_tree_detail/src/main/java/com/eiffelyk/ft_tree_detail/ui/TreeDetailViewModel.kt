package com.eiffelyk.ft_tree_detail.ui

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

class TreeDetailViewModel(private val repository: TreeDetailRepository) :
    AbsListViewModel<DataBean>() {
    private var mCid = 0
    fun setCid(cid: Int): Unit {
        mCid = cid
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
                loadDataMore(params.key, callback)
            }

            override fun loadBefore(
                params: LoadParams<Int>,
                callback: LoadCallback<Int, DataBean>
            ) {
                callback.onResult(Collections.emptyList(), null)
            }
        }
    }

    private fun loadDataMore(
        index: Int,
        callback: PageKeyedDataSource.LoadCallback<Int, DataBean>
    ) {
        viewModelScope.launch {
            val result = repository.getTreeDetailList(index, mCid)
            if (result is NetResult.Success) {
                callback.onResult(result.data.datas, index + 1)
            } else if (result is NetResult.Error) {
                Toast.makeText(
                    BaseContext.instance.getContext(),
                    result.exception.errorMsg,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun loadData(
        index: Int,
        callback: PageKeyedDataSource.LoadInitialCallback<Int, DataBean>
    ) {
        viewModelScope.launch {
            val result = repository.getTreeDetailList(index, mCid)
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

}
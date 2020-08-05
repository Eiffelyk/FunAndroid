package com.eiffelyk.ft_home.ui.project

import android.widget.Toast
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.eiffelyk.ft_home.model.project.ProjectItemSub
import com.eiffelyk.lib_base.base.AbsListViewModel
import com.eiffelyk.lib_base.utils.BaseContext
import com.eiffelyk.lib_net.model.NetResult
import kotlinx.coroutines.launch
import java.util.*


class TabItemViewModel(private val projectRepository: ProjectRepository) :
    AbsListViewModel<ProjectItemSub>() {

    private var mId: Int = 0

    fun setId(id: Int): Unit {
        mId = id
    }

    override fun createDataSource(): DataSource<Int, ProjectItemSub> {
        return object : PageKeyedDataSource<Int, ProjectItemSub>() {
            override fun loadInitial(
                params: LoadInitialParams<Int>,
                callback: LoadInitialCallback<Int, ProjectItemSub>
            ) {
                getTabPageData(1, callback)
            }

            override fun loadAfter(
                params: LoadParams<Int>,
                callback: LoadCallback<Int, ProjectItemSub>
            ) {
                getTabPageDataMore(params.key, callback)
            }

            override fun loadBefore(
                params: LoadParams<Int>,
                callback: LoadCallback<Int, ProjectItemSub>
            ) {
                callback.onResult(Collections.emptyList(), null)
            }

        }
    }

    fun getTabPageData(
        page: Int,
        callback: PageKeyedDataSource.LoadInitialCallback<Int, ProjectItemSub>
    ) {
        viewModelScope.launch {
            var data = projectRepository.getTabItemPage(page, mId)
            if (data is NetResult.Success) {
                callback.onResult(data.data.datas, null, page + 1)
            } else if (data is NetResult.Error) {
                Toast.makeText(
                    BaseContext.instance.getContext(),
                    data.exception.errorMsg,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun getTabPageDataMore(
        page: Int,
        callback: PageKeyedDataSource.LoadCallback<Int, ProjectItemSub>
    ) {
        viewModelScope.launch {
            var data = projectRepository.getTabItemPage(page, mId)
            if (data is NetResult.Success) {
                callback.onResult(data.data.datas, page + 1)
            } else if (data is NetResult.Error) {
                Toast.makeText(
                    BaseContext.instance.getContext(),
                    data.exception.errorMsg,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}
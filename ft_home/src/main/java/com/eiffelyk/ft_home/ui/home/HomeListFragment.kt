package com.eiffelyk.ft_home.ui.home

import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eiffelyk.ft_home.adapter.HomeListAdapter
import com.eiffelyk.lib_base.base.AbsListFragment
import com.eiffelyk.lib_base.datasource.MutablePageKeyDtaSource
import com.eiffelyk.lib_base.model.DataBean
import com.scwang.smart.refresh.layout.api.RefreshLayout

class HomeListFragment : AbsListFragment<DataBean, HomeListViewModel>() {
    override fun onCreateViewAfter() {
        super.onCreateViewAfter()
        hiddenActionbar()
    }

    override fun generateAdapter(): PagedListAdapter<DataBean, RecyclerView.ViewHolder> {
        return HomeListAdapter(requireContext()) as PagedListAdapter<DataBean, RecyclerView.ViewHolder>
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        mViewModel.getDataSource()!!.invalidate()
        finishRefresh()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        val currentList = mAdapter.currentList
        if (currentList == null || currentList.size <= 0) {
            return
        }
        val key = currentList.size / 20
        mViewModel.getHomeListMore(key, object : PageKeyedDataSource.LoadCallback<Int, DataBean>() {
            override fun onResult(data: MutableList<DataBean>, adjacentPageKey: Int?) {
                val dataSource = MutablePageKeyDtaSource<DataBean>()
                dataSource.data.addAll(currentList)
                dataSource.data.addAll(data)
                val pageList = dataSource.buildNewPageList(mAdapter.currentList!!.config)
                mAdapter.submitList(pageList)
            }
        })
        finishRefresh()
    }
}
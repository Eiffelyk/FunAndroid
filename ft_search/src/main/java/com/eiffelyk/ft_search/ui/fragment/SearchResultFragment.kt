package com.eiffelyk.ft_search.ui.fragment

import android.os.Bundle
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eiffelyk.ft_search.adapter.SearchResultAdapter
import com.eiffelyk.ft_search.ui.viewmodel.SearchResultViewModel
import com.eiffelyk.lib_base.base.AbsListFragment
import com.eiffelyk.lib_base.datasource.MutablePageKeyDtaSource
import com.eiffelyk.lib_base.model.DataBean
import com.scwang.smart.refresh.layout.api.RefreshLayout

class SearchResultFragment : AbsListFragment<DataBean, SearchResultViewModel>() {
    private lateinit var mPagedListAdapter: PagedListAdapter<DataBean, RecyclerView.ViewHolder>

    companion object {
        fun newInstance(hotKey: String): SearchResultFragment {
            val fragment = SearchResultFragment()
            val bundle = Bundle()
            bundle.putString("hotKey", hotKey)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateViewAfter() {
        super.onCreateViewAfter()
        hiddenActionbar()
        val hotKey = requireArguments().getString("hotKey")
        mViewModel.setHotKey(hotKey!!)
    }

    override fun generateAdapter(): PagedListAdapter<DataBean, RecyclerView.ViewHolder> {
        mPagedListAdapter =
            SearchResultAdapter(requireContext()) as PagedListAdapter<DataBean, RecyclerView.ViewHolder>
        return mPagedListAdapter
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        mViewModel.getDataSource()!!.invalidate()
        finishRefresh()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        val currentList = mPagedListAdapter.currentList
        if (currentList === null || currentList.size <= 0) {
            return
        }
        val count = currentList.size / 20
        mViewModel.loadMoreData(count, object : PageKeyedDataSource.LoadCallback<Int, DataBean>() {
            override fun onResult(data: MutableList<DataBean>, adjacentPageKey: Int?) {
                val dataSource = MutablePageKeyDtaSource<DataBean>()
                dataSource.data.addAll(currentList)
                dataSource.data.addAll(data)
                val buildNewPageList = dataSource.buildNewPageList(currentList.config)
                mPagedListAdapter.submitList(buildNewPageList)
            }

        })
    }
}
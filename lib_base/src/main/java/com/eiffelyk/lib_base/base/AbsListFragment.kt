package com.eiffelyk.lib_base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eiffelyk.lib_base.R
import com.eiffelyk.lib_base.databinding.AbsListLayoutBinding
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import org.koin.androidx.viewmodel.ext.android.getViewModel
import kotlin.reflect.KClass

abstract class AbsListFragment<T, V : AbsListViewModel<T>> : Fragment(), OnRefreshListener,
    OnLoadMoreListener {
    lateinit var mAdapter: PagedListAdapter<T, RecyclerView.ViewHolder>
    lateinit var mViewModel: V
    private lateinit var mRefreshLayout: SmartRefreshLayout
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mBinding: AbsListLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.abs_list_layout, container, false)
        mRefreshLayout = mBinding.absListSmart
        mRecyclerView = mBinding.absListRecycler
        mRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val decoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        decoration.setDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.list_drvider
            )!!
        )
        mRecyclerView.addItemDecoration(decoration)
        mAdapter = generateAdapter()
        mRecyclerView.adapter = mAdapter
        mRefreshLayout.setEnableRefresh(true)
        mRefreshLayout.setEnableLoadMore(true)
        mRefreshLayout.setOnRefreshListener(this)
        mRefreshLayout.setOnLoadMoreListener(this)
        initViewModel()
        onCreateViewAfter()
        return mBinding.root
    }

    open fun onCreateViewAfter() {
    }

    fun hiddenActionbar(): Unit {
        mBinding.absListBaseTitle.visibility = View.GONE
    }

    private fun initViewModel() {
        val clazz =
            this.javaClass.kotlin.supertypes[0].arguments[1].type!!.classifier!! as KClass<V>
        mViewModel = getViewModel<V>(clazz)
        mViewModel.getData().observe(viewLifecycleOwner, Observer { submitPageList(it) })
    }

    private fun submitPageList(it: PagedList<T>) {
        mAdapter.submitList(it)
    }

    abstract fun generateAdapter(): PagedListAdapter<T, RecyclerView.ViewHolder>

    fun finishRefresh(): Unit {
        val state = mRefreshLayout.state
        if (state.isOpening && state.isHeader) {
            mRefreshLayout.finishRefresh()
        } else if (state.isOpening && state.isFooter) {
            mRefreshLayout.finishLoadMore()
        }
    }
}
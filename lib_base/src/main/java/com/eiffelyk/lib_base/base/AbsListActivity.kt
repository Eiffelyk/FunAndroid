package com.eiffelyk.lib_base.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eiffelyk.lib_base.R
import com.eiffelyk.lib_base.databinding.AbsListLayoutBinding
import com.eiffelyk.lib_base.utils.StatusBarKt
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import org.koin.androidx.viewmodel.ext.android.getViewModel
import kotlin.reflect.KClass

abstract class AbsListActivity<T, V : AbsListViewModel<T>> : AppCompatActivity(),
    OnLoadMoreListener, OnRefreshListener {
    lateinit var mAdapter: PagedListAdapter<T, RecyclerView.ViewHolder>
    lateinit var mViewModel: V
    private lateinit var mRefreshLayout: SmartRefreshLayout
    private lateinit var mRecyclerView: RecyclerView
    lateinit var mBinding: AbsListLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        StatusBarKt.fitSystemBar(this)
        mBinding = DataBindingUtil.setContentView(this, R.layout.abs_list_layout)
        initActionBar()
        mRecyclerView = mBinding.absListRecycler
        mRefreshLayout = mBinding.absListSmart
        mRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        decoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.list_drvider)!!)
        mRecyclerView.addItemDecoration(decoration)

        mAdapter = generateAdapter()
        mRecyclerView.adapter = mAdapter
        mRefreshLayout.setEnableLoadMore(true)
        mRefreshLayout.setEnableRefresh(true)
        mRefreshLayout.setOnRefreshListener(this)
        mRefreshLayout.setOnLoadMoreListener(this)
        initViewModel()
    }

    private fun initViewModel() {
        val clazz =
            this.javaClass.kotlin.supertypes[0].arguments[1].type!!.classifier!! as KClass<V>
        mViewModel = getViewModel<V>(clazz)
        mViewModel.getData().observe(this, Observer { submitPageList(it) })
    }

    private fun submitPageList(pagedList: PagedList<T>) {
        mAdapter.submitList(pagedList)
    }

    abstract fun generateAdapter(): PagedListAdapter<T, RecyclerView.ViewHolder>

    abstract fun initActionBar()

    fun finishRefresh() {
        val state = mRefreshLayout.state
        if (state.isOpening && state.isHeader) {
            mRefreshLayout.finishRefresh()
        } else if (state.isOpening && state.isFooter) {
            mRefreshLayout.finishLoadMore()
        }
    }
}
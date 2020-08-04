package com.eiffelyk.ft_tree_detail.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.eiffelyk.ft_tree_detail.adapter.TreeDetailAdapter
import com.eiffelyk.lib_base.base.AbsListActivity
import com.eiffelyk.lib_base.model.DataBean
import com.scwang.smart.refresh.layout.api.RefreshLayout

class TreeDetailActivity : AbsListActivity<DataBean, TreeDetailViewModel>() {
    @Autowired
    @JvmField
    var cid = 0

    @Autowired
    lateinit var title: String

    companion object {
        fun start(context: Context, cid: Int, title: String): Unit {
            val intent = Intent(context, TreeDetailActivity::class.java)
            intent.putExtra("cid", cid)
            intent.putExtra("title", title)
            context.startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        super.onCreate(savedInstanceState)
        mViewModel.setCid(cid)
    }

    override fun generateAdapter(): PagedListAdapter<DataBean, RecyclerView.ViewHolder> {
        return TreeDetailAdapter(this) as PagedListAdapter<DataBean, RecyclerView.ViewHolder>
    }

    override fun initActionBar() {
        mBinding.absListBaseTitleText.text = title
        mBinding.absListBaseTitleBack.setOnClickListener { finish() }
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        finishRefresh()
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        mViewModel.getDataSource()?.invalidate()
        finishRefresh()
    }
}
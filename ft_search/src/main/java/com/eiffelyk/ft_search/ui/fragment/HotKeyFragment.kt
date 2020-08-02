package com.eiffelyk.ft_search.ui.fragment

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eiffelyk.ft_search.R
import com.eiffelyk.ft_search.adapter.SearchHotKeyAdapter
import com.eiffelyk.ft_search.databinding.HotkeyPageBinding
import com.eiffelyk.ft_search.model.HotKeyModel
import com.eiffelyk.ft_search.ui.activity.SearchActivity
import com.eiffelyk.ft_search.ui.viewmodel.HotKeyViewModel
import com.eiffelyk.lib_base.adapter.BaseRecycleViewAdapter
import com.eiffelyk.lib_base.base.BaseFragment


class HotKeyFragment : BaseFragment<HotKeyViewModel, HotkeyPageBinding>() {
    private lateinit var mRecycleView: RecyclerView

    companion object {
        fun newInstance(): HotKeyFragment {
            return HotKeyFragment()
        }
    }

    override fun getLayoutResId(): Int = R.layout.hotkey_page

    override fun initData() {
        mViewModel.getHotKey().observe(this, Observer {
            val adapter = SearchHotKeyAdapter(requireContext())
            adapter.dataList = it
            mRecycleView.adapter = adapter
            adapter.setOnItemClickListener(object :
                BaseRecycleViewAdapter.OnItemClickListener<HotKeyModel> {
                override fun onItemClick(item: HotKeyModel, position: Int) {
                    (requireActivity() as SearchActivity).setSearchEditData(item.name)
                }

            })
        })
    }

    override fun iniView() {
        mRecycleView = mViewDataBinding.hotPageRecyclerview
        mRecycleView.layoutManager = GridLayoutManager(requireContext(), 2)
    }
}

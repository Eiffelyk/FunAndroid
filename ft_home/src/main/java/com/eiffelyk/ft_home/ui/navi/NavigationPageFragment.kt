package com.eiffelyk.ft_home.ui.navi

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.eiffelyk.ft_home.R
import com.eiffelyk.ft_home.adapter.NavigationPageAdapter
import com.eiffelyk.ft_home.databinding.FragmentNavPagerBinding
import com.eiffelyk.ft_home.model.nav.NavigationItemDetail
import com.eiffelyk.lib_base.adapter.BaseRecycleViewAdapter
import com.eiffelyk.lib_base.base.BaseFragment
import com.eiffelyk.lib_base.service.webview.warp.WebViewWarpService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.koin.android.ext.android.get

class NavigationPageFragment : BaseFragment<NavigationPageViewModel, FragmentNavPagerBinding>() {

    companion object {
        fun newInstance(
            articles: MutableList<NavigationItemDetail>,
            gson: Gson
        ): NavigationPageFragment {
            val fragment = NavigationPageFragment()
            val bundle = Bundle()
            bundle.putString("data", gson.toJson(articles))
            fragment.arguments = bundle
            return fragment
        }
    }

    private var detailList: MutableList<NavigationItemDetail>? = null

    private val gson = get<Gson>()

    override fun getLayoutResId(): Int = R.layout.fragment_nav_pager

    override fun initData() {
        val arguments = requireArguments()
        val data = arguments.getString("data")
        detailList =
            gson.fromJson<MutableList<NavigationItemDetail>>(
                data,
                object : TypeToken<MutableList<NavigationItemDetail>>() {}.type
            )
    }

    override fun iniView() {
        mViewDataBinding.fragmentNavPageRecycler.layoutManager =
            GridLayoutManager(requireContext(), 2)
        val adapter = NavigationPageAdapter(requireContext())
        adapter.dataList = detailList!!
        mViewDataBinding.fragmentNavPageRecycler.adapter = adapter
        adapter.setOnItemClickListener(object :
            BaseRecycleViewAdapter.OnItemClickListener<NavigationItemDetail> {
            override fun onItemClick(item: NavigationItemDetail, position: Int) {
                WebViewWarpService.instance.start(requireContext(), item.title, item.link)
            }

        })
    }
}
package com.eiffelyk.ft_home.ui.home

import android.util.Log
import androidx.lifecycle.Observer
import com.eiffelyk.ft_home.R
import com.eiffelyk.ft_home.adapter.HomeBannerAdapter
import com.eiffelyk.ft_home.databinding.FragmentHomeBinding
import com.eiffelyk.ft_home.model.home.Banner
import com.eiffelyk.lib_base.base.BaseFragment

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override fun getLayoutResId(): Int = R.layout.fragment_home

    override fun iniView() {

        val supportFragmentManager = activity?.supportFragmentManager
        val beginTransaction = supportFragmentManager?.beginTransaction()
        beginTransaction?.replace(R.id.home_list_container, HomeListFragment::class.java, null)
        beginTransaction?.commit()

    }

    override fun initData() {

        mViewModel.apply { getBanner() }

        mViewModel.getBannerLiveData()
            .observe(viewLifecycleOwner,
                Observer<List<Banner>> {
                    mViewDataBinding.homeBanner.adapter = HomeBannerAdapter(it)
                })

    }
}
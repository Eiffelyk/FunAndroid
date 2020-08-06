package com.eiffelyk.ft_home.ui.navi

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.eiffelyk.ft_home.R
import com.eiffelyk.ft_home.`interface`.NavigationTabItemSelectedListener
import com.eiffelyk.ft_home.adapter.NavigationTabAdapter
import com.eiffelyk.ft_home.databinding.FragmentNavBinding
import com.eiffelyk.ft_home.model.nav.NavigationItem
import com.eiffelyk.lib_base.base.BaseFragment
import com.eiffelyk.lib_base.service.search.wrap.SearchServiceImpWrap
import com.eiffelyk.lib_base.utils.DensityUtil
import com.google.gson.Gson
import org.koin.android.ext.android.get


class NaviFragment : BaseFragment<NaviViewModel, FragmentNavBinding>() {

    private val gson = get<Gson>()

    private var LAST_ITEM_COUNT_LIMIT: Int = 0
    private var TAB_ITEM_OFFSET_TOP: Int = DensityUtil.dip2px(50f)
    private var TAB_ITEM_OFFSET_BOTTOM: Int = DensityUtil.dip2px(50f)
    private var mPrePosition = 0
    private var heightPixels: Int = 0

    private lateinit var mTabAdapter: NavigationTabAdapter
    private lateinit var mViewPager2: ViewPager2
    private lateinit var mTabRecycleView: RecyclerView
    override fun getLayoutResId(): Int = R.layout.fragment_nav

    override fun iniView() {

        mTabRecycleView = mViewDataBinding.fragmentNavRecycler

        mTabRecycleView.itemAnimator = null
        mTabRecycleView.animation = null
        mTabRecycleView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        mViewPager2 = mViewDataBinding.fragmentNavPaper
        mViewPager2.offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT
        mViewPager2.orientation = ViewPager2.ORIENTATION_VERTICAL

        mViewPager2.registerOnPageChangeCallback(viewPager2Callback)

        mViewDataBinding.fragmentNavTitle.setOnClickListener {
            SearchServiceImpWrap.start(requireContext())
        }

        calculateMoveSize()
    }

    private fun calculateMoveSize() {
        heightPixels = requireActivity().resources.displayMetrics.heightPixels
        heightPixels = ((heightPixels - (TAB_ITEM_OFFSET_TOP * 2).toFloat()) / 2).toInt()
        LAST_ITEM_COUNT_LIMIT = (heightPixels / TAB_ITEM_OFFSET_TOP.toFloat()).toInt()
    }

    override fun initData() {

        mViewModel.getNavigationData().observe(this, Observer {

            performTabRecycleView(it)
            performViewPagerData(it)

        })

    }

    private fun performTabRecycleView(item: MutableList<NavigationItem>) {

        mTabAdapter = NavigationTabAdapter(requireContext(), item)

        mTabAdapter.setItemSelectedListener(object : NavigationTabItemSelectedListener {
            override fun onItemSelected(itemSub: NavigationItem, position: Int) {
                mTabAdapter.setItemPositionSelected(position)
                mTabAdapter.notifyDataSetChanged()
                mViewPager2.setCurrentItem(position, false)

            }
        })

        mTabRecycleView.adapter = mTabAdapter

    }

    private fun performViewPagerData(
        item: MutableList<NavigationItem>
    ) {

        mViewPager2.adapter =
            object : FragmentStateAdapter(requireActivity().supportFragmentManager, lifecycle) {
                override fun getItemCount(): Int {
                    return item.size
                }

                override fun createFragment(position: Int): Fragment {
                    return NavigationPageFragment.newInstance(item[position].articles, gson)
                }

            }

    }

    private val viewPager2Callback = object : ViewPager2.OnPageChangeCallback() {


        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)

            performMoveEvent(position)

            mTabAdapter.setItemPositionSelected(position)
            mTabAdapter.notifyDataSetChanged()

        }
    }


    private fun performMoveEvent(position: Int) {

        if (position > mPrePosition) {  //向下滑动

            if (position * TAB_ITEM_OFFSET_TOP
                + TAB_ITEM_OFFSET_TOP > heightPixels
            ) {
                mTabRecycleView.smoothScrollBy(0, TAB_ITEM_OFFSET_TOP)
            }

        } else { //向上滑动

            if (position * TAB_ITEM_OFFSET_BOTTOM
                + TAB_ITEM_OFFSET_BOTTOM * LAST_ITEM_COUNT_LIMIT > heightPixels
                && position < (mViewPager2.adapter!!.itemCount - LAST_ITEM_COUNT_LIMIT)
            ) {

                if (position < LAST_ITEM_COUNT_LIMIT) {
                    TAB_ITEM_OFFSET_BOTTOM = TAB_ITEM_OFFSET_BOTTOM * LAST_ITEM_COUNT_LIMIT
                } else {
                    TAB_ITEM_OFFSET_BOTTOM = TAB_ITEM_OFFSET_TOP
                }

                mTabRecycleView.smoothScrollBy(
                    0,
                    -TAB_ITEM_OFFSET_BOTTOM
                )

            }
        }

        mPrePosition = position
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mViewPager2.unregisterOnPageChangeCallback(viewPager2Callback)
    }

}
package com.eiffelyk.ft_home.ui.project


import android.graphics.Typeface
import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.eiffelyk.ft_home.R
import com.eiffelyk.ft_home.databinding.FragmentProjectBinding
import com.eiffelyk.ft_home.model.project.ProjectTabItem
import com.eiffelyk.lib_base.base.BaseFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ProjectFragment : BaseFragment<ProjectViewModel, FragmentProjectBinding>() {
    private var mData: MutableList<ProjectTabItem>? = null
    private lateinit var mViewPaper: ViewPager2
    private lateinit var mTabLayout: TabLayout
    private lateinit var mediator: TabLayoutMediator
    override fun getLayoutResId(): Int = R.layout.fragment_project

    override fun initData() {
        mViewModel.getTabData().observe(this, Observer {
            mData = it
            iniView()
        })
    }

    override fun iniView() {
        mViewPaper = mViewDataBinding.fragmentProjectPager
        mTabLayout = mViewDataBinding.fragmentProjectTab
        mTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val customView = tab?.customView as TextView
                customView.textSize = 14f
                customView.typeface = Typeface.DEFAULT
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val customView = tab?.customView as TextView
                customView.textSize = 16f
                customView.typeface = Typeface.DEFAULT_BOLD
            }

        })
        mViewPaper.offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT
        mViewPaper.adapter = object : FragmentStateAdapter(childFragmentManager, lifecycle) {
            override fun getItemCount(): Int {
                if (mData != null && mData!!.size > 0) {
                    return mData!!.size
                }
                return 0
            }

            override fun createFragment(position: Int): Fragment {
                val item = mData!![position]
                return createItemFragment(item.id)
            }
        }
        mediator = TabLayoutMediator(
            mTabLayout,
            mViewPaper,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.customView = createTabView(position)
            })
        mediator.attach()
    }

    private fun createTabView(position: Int): View {
        if (mData != null && mData!!.size > 0) {
            val item = mData!![position]
            val textView = TextView(requireContext())
            textView.text = Html.fromHtml(item.name)
            return textView
        }
        return TextView(requireContext())
    }

    private fun createItemFragment(position: Int): Fragment {
        return TabItemFragment.newInstance(position)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediator.detach()
    }
}
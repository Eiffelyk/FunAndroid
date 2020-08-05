package com.eiffelyk.ft_home.ui.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.get
import androidx.navigation.ui.setupWithNavController
import com.eiffelyk.ft_home.R
import com.eiffelyk.ft_home.R.layout.activity_main
import com.eiffelyk.ft_home.databinding.ActivityMainBinding
import com.eiffelyk.lib_base.base.BaseActivity
import com.eiffelyk.lib_base.base.FixFragmentNavigator
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    private lateinit var navBar: BottomNavigationView
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
    }

    override fun getLayoutResId(): Int = activity_main

    override fun initView() {
        navBar = mViewDataBinding.bottomNav
        navController = Navigation.findNavController(this,R.id.nav_host_fragment)
        val fragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val fragmentNavigator =
            FixFragmentNavigator(this, supportFragmentManager, fragment!!.id)
        navController.navigatorProvider.addNavigator(fragmentNavigator)

        navController.setGraph(R.navigation.mobile_navigation)

        navBar.setupWithNavController(navController)
//        buttonText.setOnClickListener {
//            WebViewWarpService.instance.start(this, "百度", "https://www.baidu.com")
//        }
//        button.setOnClickListener {
//            AboutUsServiceImplWrap.start(this)
//        }
//        goSearch.setOnClickListener {
//            SearchServiceImpWrap.start(this)
//        }
//        goLogin.setOnClickListener {
//            Log.e("馋猫", "btn onClick")
//            if (!LoginServiceImplWrap.isLogin()) {
//                Log.e("馋猫", "btn onClick")
//                LoginServiceImplWrap.start(this)
//            } else {
//                Log.e("馋猫", LoginServiceImplWrap.getUserInfo().toString())
//            }
//        }
//        goTreeDetail.setOnClickListener {
//            TreeDetailServiceImplWarp.start(this,60,"你好")
//        }
    }

    override fun initData() {
    }

    override fun onBackPressed() {

//        如果调用 super.onBackPressed()，navigation会操作回退栈,切换到之前显示的页面，导致 页面叠加错乱
//        super.onBackPressed()

        val id = navController.currentDestination?.id!!
        val homeNavi = navController.graph[R.id.navigation_home].id
        if (id != homeNavi) {
            navBar.selectedItemId = R.id.navigation_home
        } else {
            finish()
        }

    }
}
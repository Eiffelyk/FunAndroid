package com.eiffelyk.ft_home.ui.main

import android.os.Bundle
import android.util.Log
import com.eiffelyk.ft_home.R
import com.eiffelyk.ft_home.databinding.ActivityMainBinding
import com.eiffelyk.lib_base.base.BaseActivity
import com.eiffelyk.lib_base.service.aboutus.warp.AboutUsServiceImplWrap
import com.eiffelyk.lib_base.service.login.warp.LoginServiceImplWrap
import com.eiffelyk.lib_base.service.search.wrap.SearchServiceImpWrap
import com.eiffelyk.lib_base.service.treedetail.wrap.TreeDetailServiceImplWarp
import com.eiffelyk.lib_base.service.webview.warp.WebViewWarpService
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
    }

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun initView() {
        buttonText.setOnClickListener {
            WebViewWarpService.instance.start(this, "百度", "https://www.baidu.com")
        }
        button.setOnClickListener {
            AboutUsServiceImplWrap.start(this)
        }
        goSearch.setOnClickListener {
            SearchServiceImpWrap.start(this)
        }
        goLogin.setOnClickListener {
            Log.e("馋猫", "btn onClick")
            if (!LoginServiceImplWrap.isLogin()) {
                Log.e("馋猫", "btn onClick")
                LoginServiceImplWrap.start(this)
            } else {
                Log.e("馋猫", LoginServiceImplWrap.getUserInfo().toString())
            }
        }
        goTreeDetail.setOnClickListener {
            TreeDetailServiceImplWarp.start(this,60,"你好")
        }
    }

    override fun initData() {
    }
}
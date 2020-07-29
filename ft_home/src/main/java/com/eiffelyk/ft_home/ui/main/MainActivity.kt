package com.eiffelyk.ft_home.ui.main

import android.os.Bundle
import android.util.Log
import com.eiffelyk.ft_home.R
import com.eiffelyk.ft_home.databinding.ActivityMainBinding
import com.eiffelyk.lib_base.base.BaseActivity
import com.eiffelyk.lib_base.service.aboutus.warp.AboutUsServiceImplWrap
import com.eiffelyk.lib_base.service.login.warp.LoginServiceImplWrap
import com.eiffelyk.lib_base.service.webview.warp.WebViewWarpService
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
    }

    override fun getLayoutResId(): Int =R.layout.activity_main

    override fun initView() {
        buttonText.setOnClickListener {
//            WebViewWarpService.instance.start(this,"百度","https://www.baidu.com")
//            AboutUsServiceImplWrap.start(this)

            Log.e("馋猫","btn onClick")
            if (!LoginServiceImplWrap.isLogin()) {
                Log.e("馋猫","btn onClick")
                LoginServiceImplWrap.start(this)
            }
        }
        button.setOnClickListener {
            AboutUsServiceImplWrap.start(this)
        }
    }

    override fun initData() {
    }
}
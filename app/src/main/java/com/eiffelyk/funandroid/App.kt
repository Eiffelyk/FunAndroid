package com.eiffelyk.funandroid

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.eiffelyk.lib_base.utils.BaseContext
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        BaseContext.instance.init(this)
//        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
//        }
        ARouter.init(this)
//        startKoin { androidContext(this@App) }
    }
}
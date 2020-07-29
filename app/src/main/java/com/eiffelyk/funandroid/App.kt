package com.eiffelyk.funandroid

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.eiffelyk.funandroid.di.allModule
import com.eiffelyk.lib_base.utils.BaseContext
import com.tencent.mmkv.MMKV
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        BaseContext.instance.init(this)
        MMKV.initialize(this)
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(allModule)
        }
    }
}
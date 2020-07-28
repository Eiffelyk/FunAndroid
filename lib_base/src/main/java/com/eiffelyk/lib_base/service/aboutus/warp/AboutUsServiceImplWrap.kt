package com.eiffelyk.lib_base.service.aboutus.warp

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.eiffelyk.lib_base.service.ConstantsPath
import com.eiffelyk.lib_base.service.aboutus.AboutUsService

object AboutUsServiceImplWrap {
    @Autowired(name = ConstantsPath.ABOUT_US_SERVICE_PATH)
    lateinit var service: AboutUsService

    init {
        ARouter.getInstance().inject(this)
    }

    fun start(context: Context) {
        service.start(context)
    }
}
package com.eiffelyk.lib_base.service.search.wrap

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.eiffelyk.lib_base.service.ConstantsPath
import com.eiffelyk.lib_base.service.search.SearchService

object SearchServiceImpWrap {
    @Autowired(name = ConstantsPath.SEARCH_SERVICE_PATH)
    lateinit var service: SearchService

    init {
        ARouter.getInstance().inject(this)
    }

    fun start(context: Context): Unit {
        service.start(context)
    }
}
package com.eiffelyk.lib_base.service.treedetail.wrap

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.eiffelyk.lib_base.service.ConstantsPath
import com.eiffelyk.lib_base.service.treedetail.TreeDetailService

object TreeDetailServiceImplWarp {
    @Autowired(name = ConstantsPath.TREE_DETAIL_SERVICE_PATH)
    lateinit var service: TreeDetailService

    init {
        ARouter.getInstance().inject(this)
    }

    fun start(context: Context, cid: Int, title: String): Unit {
        service.start(context, cid, title)
    }
}
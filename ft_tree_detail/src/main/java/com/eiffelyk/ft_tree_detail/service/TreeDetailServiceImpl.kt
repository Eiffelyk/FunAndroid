package com.eiffelyk.ft_tree_detail.service

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.eiffelyk.ft_tree_detail.ui.TreeDetailActivity
import com.eiffelyk.lib_base.service.ConstantsPath
import com.eiffelyk.lib_base.service.treedetail.TreeDetailService

@Route(path = ConstantsPath.TREE_DETAIL_SERVICE_PATH)
class TreeDetailServiceImpl : TreeDetailService {
    override fun start(context: Context, cid: Int, title: String) {
        TreeDetailActivity.start(context, cid, title)
    }

    override fun init(context: Context?) {
    }

}
package com.eiffelyk.ft_search.service

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.eiffelyk.ft_search.ui.activity.SearchActivity
import com.eiffelyk.lib_base.service.ConstantsPath
import com.eiffelyk.lib_base.service.search.SearchService

@Route(path = ConstantsPath.SEARCH_SERVICE_PATH)
class SearchServiceImpl : SearchService {
    override fun start(context: Context) {
        SearchActivity.start(context)
    }

    override fun init(context: Context?) {

    }
}
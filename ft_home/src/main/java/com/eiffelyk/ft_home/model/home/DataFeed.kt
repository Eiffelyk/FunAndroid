package com.eiffelyk.ft_home.model.home

import com.eiffelyk.lib_base.model.DataBean

data class DataFeed(
    val curPage: Int,
    val datas: MutableList<DataBean>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)
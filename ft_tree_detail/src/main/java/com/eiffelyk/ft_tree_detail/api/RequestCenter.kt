package com.eiffelyk.ft_tree_detail.api

import com.eiffelyk.ft_tree_detail.model.TreeDetailModel
import com.eiffelyk.lib_net.model.BaseModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RequestCenter {
    @GET(value = "/article/list/{count}/json")
    suspend fun getTreeDetailList(
        @Path("count") count: Int,
        @Query("cid") cid: Int
    ): BaseModel<TreeDetailModel>
}
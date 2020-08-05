package com.eiffelyk.ft_home.api

import com.eiffelyk.ft_home.model.tree.TreeData
import com.eiffelyk.lib_net.model.BaseModel
import retrofit2.http.GET

interface RequestCenter {
    @GET("/tree/json")
    suspend fun getTreeList(): BaseModel<MutableList<TreeData>>
}
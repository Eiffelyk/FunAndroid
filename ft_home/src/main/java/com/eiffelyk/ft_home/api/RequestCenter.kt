package com.eiffelyk.ft_home.api

import com.eiffelyk.ft_home.model.project.ProjectPageItem
import com.eiffelyk.ft_home.model.project.ProjectTabItem
import com.eiffelyk.ft_home.model.tree.TreeData
import com.eiffelyk.lib_net.model.BaseModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RequestCenter {
    @GET("/tree/json")
    suspend fun getTreeList(): BaseModel<MutableList<TreeData>>

    @GET("/project/tree/json")
    suspend fun getProjectTabData(): BaseModel<MutableList<ProjectTabItem>>

    @GET("/project/list/{count}/json")
    suspend fun getProjectItemList(
        @Path("count") count: Int,
        @Query("cid") cid: Int
    ): BaseModel<ProjectPageItem>
}
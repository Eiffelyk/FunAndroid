package com.eiffelyk.ft_home.ui.tree

import com.eiffelyk.ft_home.api.RequestCenter
import com.eiffelyk.ft_home.model.tree.TreeData
import com.eiffelyk.lib_net.model.NetResult
import com.eiffelyk.lib_net.net.BaseRepository
import com.eiffelyk.lib_net.net.RetrofitClient

class TreeRepository(private val retrofitClient: RetrofitClient) : BaseRepository() {
    suspend fun getTreeList(): NetResult<MutableList<TreeData>> {
        return callRequest { requestTreeList() }
    }

    private suspend fun requestTreeList() =
        handlerResponse(retrofitClient.create(RequestCenter::class.java).getTreeList())
}
package com.eiffelyk.ft_tree_detail.ui

import com.eiffelyk.ft_tree_detail.api.RequestCenter
import com.eiffelyk.ft_tree_detail.model.TreeDetailModel
import com.eiffelyk.lib_net.model.NetResult
import com.eiffelyk.lib_net.net.BaseRepository
import com.eiffelyk.lib_net.net.RetrofitClient

class TreeDetailRepository(private val retrofitClient: RetrofitClient) : BaseRepository() {
    suspend fun getTreeDetailList(count: Int, cid: Int): NetResult<TreeDetailModel> {
        return callRequest { requestTreeDetailList(count, cid) }
    }

    private suspend fun requestTreeDetailList(count: Int, cid: Int) = handlerResponse(
        retrofitClient.create(RequestCenter::class.java).getTreeDetailList(count, cid)
    )
}
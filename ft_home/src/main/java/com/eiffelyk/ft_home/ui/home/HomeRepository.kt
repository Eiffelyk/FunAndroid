package com.eiffelyk.ft_home.ui.home

import com.eiffelyk.ft_home.api.RequestCenter
import com.eiffelyk.ft_home.model.home.Banner
import com.eiffelyk.ft_home.model.home.DataFeed
import com.eiffelyk.lib_net.model.NetResult
import com.eiffelyk.lib_net.net.BaseRepository
import com.eiffelyk.lib_net.net.RetrofitClient

class HomeRepository(private val retrofitClient: RetrofitClient) : BaseRepository() {
    suspend fun getBanner(): NetResult<MutableList<Banner>> {
        return callRequest { requestBanner() }
    }

    private suspend fun requestBanner() =
        handlerResponse(retrofitClient.create(RequestCenter::class.java).getBanner())

    suspend fun getHomeList(count: Int): NetResult<DataFeed> {
        return callRequest { requestHomeList(count) }
    }

    private suspend fun requestHomeList(count: Int) =
        handlerResponse(retrofitClient.create(RequestCenter::class.java).geHomeList(count))
}
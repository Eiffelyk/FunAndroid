package com.eiffelyk.ft_search.ui.repository

import com.eiffelyk.ft_search.api.RequestCenter
import com.eiffelyk.ft_search.model.HotKeyModel
import com.eiffelyk.ft_search.model.SearchResultModel
import com.eiffelyk.lib_net.model.NetResult
import com.eiffelyk.lib_net.net.BaseRepository
import com.eiffelyk.lib_net.net.RetrofitClient

class SearchRepository(private val service: RetrofitClient) : BaseRepository() {
    suspend fun getHotKey(): NetResult<MutableList<HotKeyModel>> {
        return callRequest { requestHotKey() }
    }

    private suspend fun requestHotKey() =
        handlerResponse(service.create(RequestCenter::class.java).getHotKey())

    suspend fun search(page: Int, key: String): NetResult<SearchResultModel> {
        return callRequest { requestSearch(page, key) }
    }

    private suspend fun requestSearch(page: Int, key: String) =
        handlerResponse(service.create(RequestCenter::class.java).search(page, key))
}
package com.eiffelyk.ft_home.ui.navi

import com.eiffelyk.ft_home.api.RequestCenter
import com.eiffelyk.ft_home.model.nav.NavigationItem
import com.eiffelyk.lib_net.model.NetResult
import com.eiffelyk.lib_net.net.BaseRepository
import com.eiffelyk.lib_net.net.RetrofitClient

class NaviRepository(private val retrofitClient: RetrofitClient) : BaseRepository() {
    suspend fun getNavigationData(): NetResult<MutableList<NavigationItem>> {
        return callRequest { requestNavigation() }
    }

    private suspend fun requestNavigation() =
        handlerResponse(retrofitClient.create(RequestCenter::class.java).getNavigationData())
}
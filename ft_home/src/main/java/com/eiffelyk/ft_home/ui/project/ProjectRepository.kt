package com.eiffelyk.ft_home.ui.project

import com.eiffelyk.ft_home.api.RequestCenter
import com.eiffelyk.ft_home.model.project.ProjectPageItem
import com.eiffelyk.ft_home.model.project.ProjectTabItem
import com.eiffelyk.lib_net.model.NetResult
import com.eiffelyk.lib_net.net.BaseRepository
import com.eiffelyk.lib_net.net.RetrofitClient

class ProjectRepository(private val retrofitClient: RetrofitClient) : BaseRepository() {
    suspend fun getTabData(): NetResult<MutableList<ProjectTabItem>> {
        return callRequest { requestTabData() }
    }

    private suspend fun requestTabData() =
        handlerResponse(retrofitClient.create(RequestCenter::class.java).getProjectTabData())

    suspend fun getTabItemPage(count: Int, cid: Int): NetResult<ProjectPageItem> {
        return callRequest { requestPageData(count, cid) }
    }

    private suspend fun requestPageData(count: Int, cid: Int) = handlerResponse(
        retrofitClient.create(RequestCenter::class.java).getProjectItemList(count, cid)
    )
}
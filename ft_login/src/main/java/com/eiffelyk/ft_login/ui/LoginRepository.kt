package com.eiffelyk.ft_login.ui

import com.eiffelyk.ft_login.api.RequestCenter
import com.eiffelyk.lib_base.model.User
import com.eiffelyk.lib_net.model.NetResult
import com.eiffelyk.lib_net.net.BaseRepository
import com.eiffelyk.lib_net.net.RetrofitClient

class LoginRepository(private val service: RetrofitClient) : BaseRepository() {
    suspend fun login(userName: String, password: String): NetResult<User> {
        return callRequest { requestLogin(userName, password) }
    }

    private suspend fun requestLogin(userName: String, password: String): NetResult<User> =
        handlerResponse(service.create(RequestCenter::class.java).login(userName, password))

    suspend fun register(userName: String, password: String, rePassword: String): NetResult<User> {
        return callRequest { requestRegister(userName, password, rePassword) }
    }

    private suspend fun requestRegister(
        userName: String,
        password: String,
        rePassword: String
    ): NetResult<User> =
        handlerResponse(
            service.create(RequestCenter::class.java).register(userName, password, rePassword)
        )
}
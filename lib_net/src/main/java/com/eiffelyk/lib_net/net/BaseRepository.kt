package com.eiffelyk.lib_net.net

import com.eiffelyk.lib_net.exception.DealException
import com.eiffelyk.lib_net.exception.ResultException
import com.eiffelyk.lib_net.model.BaseModel
import com.eiffelyk.lib_net.model.NetResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

open class BaseRepository {
    suspend fun <T : Any> callRequest(call: suspend () -> NetResult<T>): NetResult<T> {
        return try {
            call()
        } catch (e: Exception) {
            e.printStackTrace()
            NetResult.Error(DealException.handler(e))
        }
    }

    suspend fun <T : Any> handlerResponse(response: BaseModel<T>, successBlock: (suspend CoroutineScope.() -> Unit)? = null, errorBlock: (suspend CoroutineScope.() -> Unit)? = null): NetResult<T> {
        return coroutineScope {
            if (response.errorCode == -1) {
                successBlock?.let { it() }
                NetResult.Error(ResultException(response.errorCode.toString(), response.errorMsg))
            } else {
                errorBlock?.let { it() }
                NetResult.Success(response.data)
            }
        }
    }
}
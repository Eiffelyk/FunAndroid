package com.eiffelyk.lib_net.intercepter

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class CommonInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = addHeader(request.newBuilder())
        return chain.proceed(builder)
    }

    private fun addHeader(newBuilder: Request.Builder): Request {
        return newBuilder.addHeader("Content_Type", "application/json")
            .addHeader("charset", "UTF-8").build()
    }
}

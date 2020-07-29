package com.eiffelyk.lib_net.net

import com.eiffelyk.lib_net.BuildConfig
import com.eiffelyk.lib_net.intercepter.CommonInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient private constructor() {
    private var retrofit: Retrofit

    companion object {
        val instance: RetrofitClient by lazy { RetrofitClient() }
    }

    init {
        retrofit = Retrofit.Builder().client(initRetrofit())
            .addConverterFactory(GsonConverterFactory.create()).baseUrl(ApiService.BASE_URL).build()
    }

    private fun initRetrofit(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(initLogInterceptor())
            .addInterceptor(CommonInterceptor())
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    private fun initLogInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor =HttpLoggingInterceptor()
        if (BuildConfig.DEBUG){
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }else{
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        }
        return httpLoggingInterceptor
    }

    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }
}

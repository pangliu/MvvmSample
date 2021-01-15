package com.example.mvvm_test.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class CoroutineService {
    companion object {
        private var mInstance: CoroutineService? = null
        private lateinit var coroutinesApiStores: CoroutineStores

        fun getInstance(): CoroutineService? {
            if(mInstance == null) {
                synchronized(CoroutineService::class) {
                    if(mInstance == null) {
                        mInstance = CoroutineService()
                    }
                }
            }
            return mInstance
        }
    }

    init {
        val client = OkHttpClient.Builder()
            .connectTimeout(RxService.ApiConfig.TIME_OUT_CONNECT.toLong(), TimeUnit.SECONDS)
            .readTimeout(RxService.ApiConfig.TIME_OUT_READ.toLong(), TimeUnit.SECONDS)
            .writeTimeout(RxService.ApiConfig.TIME_OUT_WRITE.toLong(), TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(RxService.ApiConfig.WEB_HOST)
            .client(client)
            .build()

        coroutinesApiStores = retrofit.create(CoroutineStores::class.java)
    }

    fun getStores(): CoroutineStores = coroutinesApiStores
}
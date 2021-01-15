package com.example.mvvm_test.api

import com.example.mvvm_test.api.RxService.ApiConfig.TIME_OUT_CONNECT
import com.example.mvvm_test.api.RxService.ApiConfig.TIME_OUT_READ
import com.example.mvvm_test.api.RxService.ApiConfig.TIME_OUT_WRITE
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RxService {

    object ApiConfig {
        const val TIME_OUT_CONNECT = 30
        const val TIME_OUT_READ = 30
        const val TIME_OUT_WRITE = 30

        const val WEB_HOST = "https://run.mocky.io/"
//        const val loginUrl = "https://run.mocky.io/v3/2c9fcbc6-440b-425e-8b96-e470e42962cf"
        const val loginUrl = "https://run.mocky.io/v3/87605dd6-db69-468f-936d-4367e23ad351"
        const val UNSPLASH_URL = "https://api.unsplash.com/"
    }

    var rxApiStores: RxStores

    init {
        val client = OkHttpClient.Builder()
            .connectTimeout(TIME_OUT_CONNECT.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIME_OUT_READ.toLong(), TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT_WRITE.toLong(), TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApiConfig.WEB_HOST)
            .client(client)
            .build()

        rxApiStores = retrofit.create(RxStores::class.java)
    }
}
package com.example.mvvm_test.api

import com.example.mvvm_test.model.LoginReq
import com.example.mvvm_test.model.LoginResp
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CoroutineStores {

    @POST(RxService.ApiConfig.loginUrl)
    fun login(@Body request: LoginReq): Deferred<Response<LoginResp>>

    @POST(RxService.ApiConfig.loginUrl)
    suspend fun flowLogin(@Body request: LoginReq): LoginResp

}
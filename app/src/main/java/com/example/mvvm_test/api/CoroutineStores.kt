package com.example.mvvm_test.api

import com.example.mvvm_test.model.LoginReq
import com.example.mvvm_test.model.LoginResp
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CoroutineStores {

    @POST(NetworkService.ApiConfig.loginUrl)
    fun login(@Body request: LoginReq): Deferred<Response<LoginResp>>

    @POST(NetworkService.ApiConfig.loginUrl)
    suspend fun flowLogin(@Body request: LoginReq): LoginResp
}
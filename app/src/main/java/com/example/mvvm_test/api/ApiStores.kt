package com.example.mvvm_test.api

import com.example.mvvm_test.model.LoginReq
import com.example.mvvm_test.model.LoginResp
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiStores {

    @POST(NetworkService.ApiConfig.loginUrl)
    fun login(@Body request: LoginReq): Single<Response<LoginResp>>
}
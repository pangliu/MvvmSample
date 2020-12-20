package com.example.mvvm_test.model

import android.util.Log
import com.example.mvvm_test.api.NetworkService
import io.reactivex.Single

class Repository {

    fun login(account: String, password: String): Single<LoginResp> {
        val networkService = NetworkService()
        val request = LoginReq(account, password)

        return networkService.memberApi.login(request).map {
            Log.d("msg", "api code: ${it.code()}")
            it.body()
        }
    }
}
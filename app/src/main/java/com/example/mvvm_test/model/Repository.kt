package com.example.mvvm_test.model

import android.util.Log
import com.example.mvvm_test.api.CoroutineService
import com.example.mvvm_test.api.CoroutineStores
import com.example.mvvm_test.api.NetworkService
import io.reactivex.Single
import io.reactivex.annotations.SchedulerSupport.IO
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import retrofit2.Response

class Repository(private val apiStores: CoroutineStores) {

    // 使用 Rxjava2
    fun login(account: String, password: String): Single<LoginResp> {
        val networkService = NetworkService()
        val request = LoginReq(account, password)

        return networkService.rxApiStores.login(request).map {
            Log.d("msg", "api code: ${it.code()}")
            it.body()
        }
    }

    // 方法一
    suspend fun coroutineLogin(account: String, password: String): Response<LoginResp> {
        val coroutineService = CoroutineService()
        val request = LoginReq(account, password)
//        var resp = coroutineService.coroutinesApiStores.login(request).await()
        var resp = apiStores.login(request).await()
        Log.d("msg", "api code: ${resp.code()}")
        Log.d("msg", "api resp: ${resp.body()}")
        return resp
    }
    // 方法二，使用 ViewState & Flow
    fun coroutineLogin2(account: String, password: String): Flow<ViewState<LoginResp>> {
//        val coroutineService = CoroutineService()
        val request = LoginReq(account, password)
        return flow {
            emit(ViewState.loading())
            // 可在此連續呼叫 api
            val resp = apiStores.flowLogin(request)
            emit(ViewState.success(resp))
        }.catch {
            emit(ViewState.error(it.message.orEmpty()))
        }.flowOn(Dispatchers.IO)
    }

}
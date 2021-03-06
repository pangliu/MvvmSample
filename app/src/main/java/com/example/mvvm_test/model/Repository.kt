package com.example.mvvm_test.model

import android.util.Log
import com.example.mvvm_test.api.CoroutineService
import com.example.mvvm_test.api.CoroutineStores
import com.example.mvvm_test.api.RxService
import com.example.mvvm_test.room.AccountEntity
import com.example.mvvm_test.room.LocalDataBase
import io.reactivex.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class Repository(private val apiStores: CoroutineStores, private val localDb: LocalDataBase) {

    // 使用 Rxjava2
    fun login(account: String, password: String): Single<LoginResp> {
        val networkService = RxService()
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

    fun insertAccount(
        type: String,
        account: String,
        phone: String,
        photo: String
    ): Flow<ViewState<Long>>{
        val accountEntity = AccountEntity(null, type, account, phone, photo)
        return flow {
            emit(ViewState.loading())
            val resp = localDb.accountDao().insertAccount(accountEntity)
            emit(ViewState.success(resp))
        }.catch {
            emit(ViewState.error(it.message.orEmpty()))
        }.flowOn(Dispatchers.IO)
    }

    fun getAllAccount(): Flow<ViewState<MutableList<AccountEntity>>> {
        return flow {
            emit(ViewState.loading())
//            Log.d("msg", "thread name: ${Thread.currentThread().name}")
            val resp = localDb.accountDao().getAllAccount()
            emit(ViewState.success(resp))
        }.catch {
            emit(ViewState.error(it.message.orEmpty()))
        }.flowOn(Dispatchers.IO)
    }

    fun getUnsplashStream() {

    }

}
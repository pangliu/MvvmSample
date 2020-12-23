package com.example.mvvm_test.mvvm

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_test.base.SharePreferencesProvider
import com.example.mvvm_test.model.Repository
import com.example.mvvm_test.model.ViewState
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.SchedulerPoolFactory
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class LoginViewModel(val repository: Repository, val preferences: SharePreferencesProvider): ViewModel() {

    // 載入中
    var isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
//    var repository = Repository()

    // 使用 Rxjava2
    @SuppressLint("CheckResult")
    fun login(account: String, password: String) {
        // 開始 loading
        isLoading.value = true
//        val repository = Repository()
        repository.login(account, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                Log.d("msg", "loginResponse: $data")
                // 關閉 loading
                isLoading.value = false
            },{ throwable ->
                Log.d("msg", "api error: $throwable")
                // 關閉 loading
                isLoading.value = false
            })
    }
    // 使用 Coroutine
    fun coroutineLogin(account: String, password: String) {
        /**
         * 方法一
         */
//        viewModelScope.launch {
//            isLoading.value = true
//            var resp = repository.coroutineLogin(account, password)
//            if (resp.isSuccessful) {
//                Log.d("msg", "first login success")
//            } else {
//                Log.d("msg", "first login failed")
//            }
//            isLoading.value = false
//        }
        /**
         * 方法二，使用 ViewState & Flow
         */
        viewModelScope.launch {
            repository.coroutineLogin2(account, password).collect { state ->
                when(state) {
                    is ViewState.Success -> {
                        isLoading.value = false
                        Log.d("msg", "login resp: ${state.data}")
                        Log.d("msg", "login success")
                    }
                    is ViewState.Loading -> {
                        isLoading.value = true
                        Log.d("msg", "login loading")
                    }
                    is ViewState.Error -> {
                        isLoading.value = false
                        Log.d("msg", "login failed")
                        state.message
                    }
                }
            }
        }
    }
}
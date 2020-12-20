package com.example.mvvm_test.mvvm

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm_test.model.Repository
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.SchedulerPoolFactory
import io.reactivex.schedulers.Schedulers

class LoginViewModel: ViewModel() {

    // 載入中
    var isLoading: MutableLiveData<Boolean> = MutableLiveData(true)

    @SuppressLint("CheckResult")
    fun login(account: String, password: String) {
        // 開始 loading
        isLoading.value = true
        val repository = Repository()
        repository.login(account, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                Log.d("msg", "loginResponse: $data")
                // 關閉 loading
                isLoading.value = false
            },{ throwable ->
                Log.d("msg", "api error: $throwable")
                isLoading.value = false
            })
    }

}
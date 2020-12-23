package com.example.mvvm_test.base

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

class BaseApplication: Application(), ViewModelStoreOwner {

    private lateinit var mAppViewModelStore: ViewModelStore
    override fun onCreate() {
        super.onCreate()
        Log.d("msg", "BaseApplication")
        mAppViewModelStore = ViewModelStore()
        AppInjector.init(this, ViewModelFactory())
    }

    override fun getViewModelStore(): ViewModelStore = mAppViewModelStore
}
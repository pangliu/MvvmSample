package com.example.mvvm_test.base

import android.app.Application
import android.content.SharedPreferences
import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_test.model.Repository
import com.example.mvvm_test.mvvm.LoginViewModel

class ViewModelFactory(
    override var mApplication: Application? = null,
    override var mRepository: Repository? = null,
    override var mResources: Resources? = null,
    override var mSharePrefences: SharePreferencesProvider? = null
) : BaseViewModelFactory() {

    override fun init(
        application: Application,
        repository: Repository,
        resources: Resources,
        sharePreferences: SharePreferencesProvider
    ) {
        mApplication = application
        mRepository = repository
        mResources = resources
        mSharePrefences = sharePreferences
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(repository = mRepository!!, preferences = mSharePrefences!!)
                else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            } as T
        }
    }
}
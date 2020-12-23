package com.example.mvvm_test.base

import android.app.Application
import android.content.res.Resources
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_test.model.Repository

abstract class BaseViewModelFactory: ViewModelProvider.NewInstanceFactory() {
    abstract var mApplication: Application?
    abstract var mRepository: Repository?
    abstract var mResources: Resources?
    abstract var mSharePrefences: SharePreferencesProvider?

    abstract fun init(
        application: Application,
        repository: Repository,
        resources: Resources,
        sharePreferences: SharePreferencesProvider
    )
}
package com.example.mvvm_test.base

import android.app.Activity
import android.app.Application
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.mvvm_test.api.CoroutineService
import com.example.mvvm_test.model.Repository

object AppInjector {

    private lateinit var mApplication: Application
    private lateinit var mRepository: Repository
    private lateinit var mSharePreferencesProvider: SharePreferencesProvider
    private lateinit var mResources: Resources
    private lateinit var mViewModelFactory: BaseViewModelFactory

    fun init(application: Application, factory: ViewModelFactory) {
        mApplication = application
        mApplication.let {
            mResources = application.resources
            mSharePreferencesProvider = SharePreferencesProvider(it)
            mRepository = Repository(CoroutineService.getInstance()?.getStores()!!)
            factory.init(mApplication, mRepository, mResources, mSharePreferencesProvider)
            mViewModelFactory = factory
        }
    }

    fun getViewModelFactory(): BaseViewModelFactory = mViewModelFactory

    inline fun <reified T : ViewModel> obtainViewModel(activity: Activity): T =
        ViewModelProvider(activity as AppCompatActivity, getViewModelFactory()).get(T::class.java)

    inline fun <reified T: ViewModel> obtainViewModel(fragment: Fragment): T =
        ViewModelProvider(fragment as Fragment, getViewModelFactory()).get(T::class.java)

    inline fun <reified T: ViewModel> obtainViewModel(application: Application): T =
        ViewModelProvider(application as ViewModelStoreOwner, getViewModelFactory()).get(T::class.java)
}
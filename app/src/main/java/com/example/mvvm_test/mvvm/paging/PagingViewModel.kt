package com.example.mvvm_test.mvvm.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm_test.base.SharePreferencesProvider
import com.example.mvvm_test.model.Repository

class PagingViewModel(
    val repository: Repository,
    val preferences: SharePreferencesProvider): ViewModel() {

    var isLoading = MutableLiveData<Boolean>(false)

}
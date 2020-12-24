package com.example.mvvm_test.mvvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_test.base.SharePreferencesProvider
import com.example.mvvm_test.model.Repository
import com.example.mvvm_test.model.ViewState
import com.example.mvvm_test.room.LocalDataBase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(
    val repository: Repository,
    val preferences: SharePreferencesProvider
): ViewModel() {
    var isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    fun insertAccount(account: String, type: String) {
        viewModelScope.launch {
            repository.inserAccount(account, type).collect { state ->
                when(state) {
                    is ViewState.Loading -> {
                        isLoading.value = true
                        Log.d("msg", "add account loading")
                    }
                    is ViewState.Success -> {
                        isLoading.value = false
                        Log.d("msg", "add account resp: ${state.data}")
                        Log.d("msg", "add account success")
                    }
                    is ViewState.Error -> {
                        isLoading.value = false
                        Log.d("msg", "add account failed")
                    }
                }
            }
        }
    }

    fun getAllAccount() {
        viewModelScope.launch {
            repository.getAllAccount().collect { state->
                when(state) {
                    is ViewState.Loading->{
                        isLoading.value = true
                        Log.d("msg", "get account loading")
                    }
                    is ViewState.Success ->{
                        isLoading.value = false
                        Log.d("msg", "data: ${state.data}")
                        Log.d("msg", "get account success")
                    }
                    is ViewState.Error -> {
                        isLoading.value = false
                        Log.d("msg", "get account failed")
                    }
                }
            }
        }
    }
}
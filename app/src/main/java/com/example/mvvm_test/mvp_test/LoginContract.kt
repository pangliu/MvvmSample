package com.example.mvvm_test.mvp_test

interface LoginContract {
    interface ILoginPresenter {
        fun login(id: String, password: String)
    }
    interface ILoginView {
        fun onLoginSuccess(message: String)
        fun onLoginFail(message: String)
    }
}
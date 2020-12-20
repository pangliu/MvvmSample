package com.example.mvvm_test.mvp_test

interface ILoginRepository {
    fun login(loginId: String, password: String, listener:LoginRepository.LoginCallback)
}
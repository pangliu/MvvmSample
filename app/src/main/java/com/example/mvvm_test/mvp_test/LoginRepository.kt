package com.example.mvvm_test.mvp_test

import android.os.Handler

class LoginRepository: ILoginRepository {

    interface LoginCallback {
        fun loginResult(isLoginSuccess: Boolean)
    }
    override fun login(
        loginId: String, password: String,
        listener: LoginCallback) {
        //模擬1.5秒後回傳登入結果
        Handler().postDelayed({
            if (loginId == "abc" && password == "123") {
                listener.loginResult(true)
            } else {
                listener.loginResult(false)
            }
        }, 1500)
    }
}
package com.example.mvvm_test.mvp_test

class LoginPresenter constructor(private val view: LoginContract.ILoginView):
    LoginContract.ILoginPresenter {

    override fun login(id: String, password: String) {
        val loginRepository = LoginRepository()
        loginRepository.login(
            id,
            password,
            object : LoginRepository.LoginCallback {
                override fun loginResult(isLoginSuccess: Boolean) {
                    if (isLoginSuccess) {
                        view.onLoginSuccess("登入成功")
                    } else {
                        view.onLoginFail("帳密錯誤")
                    }
                }
            })
    }

}
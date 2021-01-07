package com.example.mvvm_test.mvp_test

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.mvvm_test.R
import kotlinx.android.synthetic.main.fragment_login.*

class MvpLoginFragment: Fragment(), LoginContract.ILoginView {

    private lateinit var presenter: LoginContract.ILoginPresenter
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("msg", "Login onViewCreated")
        val navController = NavHostFragment.findNavController(this)
        btn_home_page.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_homeFragment)
        }
    }
    override fun onLoginSuccess(message: String) {
    }

    override fun onLoginFail(message: String) {
    }
}
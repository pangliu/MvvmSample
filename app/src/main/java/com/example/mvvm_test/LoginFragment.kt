package com.example.mvvm_test

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.mvvm_test.base.AppInjector
import com.example.mvvm_test.base.invisible
import com.example.mvvm_test.base.visible
import com.example.mvvm_test.databinding.FragmentLoginBinding
import com.example.mvvm_test.mvvm.LoginViewModel


class LoginFragment: Fragment() {
    private lateinit var viewMode: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("msg", "Login onViewCreated")
        viewMode = AppInjector.obtainViewModel(this)
//        viewMode = ViewModelProvider(this, ViewModelFactory()).get(LoginViewModel::class.java)
        initView()
    }

    private fun initView() {
        val navController = findNavController(this)
        viewMode.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            Log.d("msg", "isLoading: $isLoading")
            if(isLoading) {
                binding.progress.visible()
            } else {
                binding.progress.invisible()
            }
        })
        binding.btnLogin.setOnClickListener {
            // 呼叫 Rxjava api
//            viewMode.login(binding.etAccount.text.toString(), binding.etPasswrod.text.toString())
            // 呼叫 Coroutine api
            viewMode.coroutineLogin(binding.etAccount.text.toString(), binding.etPasswrod.text.toString())
        }
        binding.btnGoTo.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_homeFragment)
        }
    }

}
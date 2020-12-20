package com.example.mvvm_test

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.mvvm_test.databinding.FragmentLoginBinding
import com.example.mvvm_test.mvp_test.LoginContract
import com.example.mvvm_test.mvvm.LoginViewModel
import com.example.mvvm_test.mvvm.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_login.*


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
        viewMode = ViewModelProvider(this, ViewModelFactory()).get(LoginViewModel::class.java)
        initView()
    }

    private fun initView() {
        val navController = findNavController(this)
        viewMode.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            if(isLoading) {
                binding.progress.visibility = View.VISIBLE
            } else {
                binding.progress.visibility = View.INVISIBLE
            }
        })
        binding.btnLogin.setOnClickListener {
            viewMode.login(binding.etAccount.text.toString(), binding.etPasswrod.text.toString())
        }
        binding.btnGoTo.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_homeFragment)
        }
    }

}
package com.example.mvvm_test.mvvm

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.mvvm_test.base.AppInjector
import com.example.mvvm_test.base.invisible
import com.example.mvvm_test.base.onClick
import com.example.mvvm_test.base.visible
import com.example.mvvm_test.databinding.FragmentHomeBinding

class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        Log.d("msg", "onCreateView")
        viewModel = AppInjector.obtainViewModel<HomeViewModel>(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var textStr: String? = "adb"
        textStr = null
        initView()
    }

    private fun initView() {
        binding.btnAddAccount.onClick {
//            viewModel.insertAccount("hank", "收入")
            viewModel.getAllAccount()
        }
        viewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            if(isLoading) {
                binding.dbProgress.visible()
            } else {
                binding.dbProgress.invisible()
            }
        })

    }
}
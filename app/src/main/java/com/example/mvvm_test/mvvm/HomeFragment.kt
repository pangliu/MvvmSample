package com.example.mvvm_test.mvvm

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_test.base.AppInjector
import com.example.mvvm_test.base.invisible
import com.example.mvvm_test.base.onClick
import com.example.mvvm_test.base.visible
import com.example.mvvm_test.databinding.FragmentHomeBinding
import com.example.mvvm_test.room.AccountEntity
import kotlin.random.Random

class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var accountAdapter: AccountAdapter

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        viewModel = AppInjector.obtainViewModel<HomeViewModel>(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        accountAdapter = AccountAdapter(mutableListOf())
        binding.rvAccount.apply {
            var layoutManager = LinearLayoutManager(context)
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            this.layoutManager = layoutManager
            this.adapter = accountAdapter
        }
        binding.btnAddAccount.onClick {
//            viewModel.insertAccount("hank", "收入")
            generateData()
        }
        binding.btnGetAllAccount.onClick {
            viewModel.getAllAccount()
        }
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if(isLoading) {
                binding.dbProgress.visible()
            } else {
                binding.dbProgress.invisible()
            }
        }
        viewModel.accountLive.observe(viewLifecycleOwner) { accounts ->
            Log.d("msg", "accountLive: $accounts")
            accountAdapter.data = accounts
            accountAdapter.notifyDataSetChanged()
        }
    }

    private fun generateData() {
        for (i in 0..10) {
            var nameTag = "hank" + i
            var type = Random.nextInt(0, 10).toString()
            var phone = Random.nextInt(0, 10000).toString()
            var photo = "http://" + Random.nextInt(10000, 10000000)
            viewModel.insertAccount(type, nameTag, phone, photo)
        }
    }
}
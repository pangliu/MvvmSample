package com.example.mvvm_test.mvvm.paging

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_test.base.AppInjector
import com.example.mvvm_test.databinding.FragmentPagingBinding

class PagingFragment: Fragment() {
    private lateinit var viewMode: PagingViewModel
    private lateinit var binding :  FragmentPagingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPagingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewMode = AppInjector.obtainViewModel(this)
        initView()
    }

    fun initView() {
        binding.rvPaging.apply {
            this.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            this.setHasFixedSize(true)
//            var layoutManager = LinearLayoutManager(context)
//            layoutManager.orientation = LinearLayoutManager.VERTICAL
            this.layoutManager = layoutManager
        }
    }

}
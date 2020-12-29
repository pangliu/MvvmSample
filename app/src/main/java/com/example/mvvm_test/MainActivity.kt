package com.example.mvvm_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
//        val someflow = flow {
//            listOf(9, 5, 2, 7).forEach {
//                log("emit: $it")
//                emit(it)
//            }
//        }
//        runBlocking {
//            someflow
//                .flowOn(Dispatchers.IO)
//                .collect {
//                log("collect $it")
//            }
//        }
    }

    fun log(msg: String) = Log.d("msg", "[${Thread.currentThread().name}] $msg")
}
package com.example.mvvm_test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

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
        var dd: Double = 1000.0
        var dec = DecimalFormat("#.00")
        val principalMoney = String.format("%.2f", dd)
        Log.d("msg", "principalMoney: $principalMoney")
    }

    fun log(msg: String) = Log.d("msg", "[${Thread.currentThread().name}] $msg")
}
package com.example.mvvm_test.model

import com.google.gson.annotations.SerializedName


data class LoginResp(
    val code: Int,
    val errorMsg: String
//    val memberId: String,
//    @SerializedName("mdmberName")
//    val name: String
)

data class LoginReq(
        val account: String,
        val password: String
)
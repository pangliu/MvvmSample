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

data class UnsplashPhotoUrls(
    @field:SerializedName("small") val small: String
)

data class UnsplashUser(
    @field:SerializedName("name") val name: String,
    @field:SerializedName("username") val username: String
) {
    val attributionUrl: String
        get() {
            return "https://unsplash.com/$username?utm_source=sunflower&utm_medium=referral"
        }
}

data class UnsplashPhoto(
    @field:SerializedName("id") val id: String,
    @field:SerializedName("urls") val urls: UnsplashPhotoUrls,
    @field:SerializedName("user") val user: UnsplashUser
)

data class UnsplashSearchResponse(
    @field:SerializedName("results") val results: List<UnsplashPhoto>,
    @field:SerializedName("total_pages") val totalPages: Int
)
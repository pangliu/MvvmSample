package com.example.mvvm_test.base

import android.app.Application
import android.content.Context
import androidx.core.content.edit

class SharePreferencesProvider(private val application: Application) {
    private val sharedPreferences = application.getSharedPreferences(NAME, Context.MODE_PRIVATE)

    companion object {
        private const val NAME = "sharePreferencesData"
        const val ACCOUNT = "account"
        const val PASSWORD = "password"
        const val REMEMBER = "remember"
    }

    var account: String
        get() = sharedPreferences.getString(ACCOUNT, "")!!
        set(account) = sharedPreferences.edit { putString(ACCOUNT, account) }

    var password: String
        get() = sharedPreferences.getString(PASSWORD, "")!!
        set(password) = sharedPreferences.edit { putString(PASSWORD, password) }

    var remember: Boolean
        get() = sharedPreferences.getBoolean(REMEMBER, false)
        set(remember) = sharedPreferences.edit { putBoolean(REMEMBER, remember) }
}
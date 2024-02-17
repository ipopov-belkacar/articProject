package com.example.goncharov1.utils

import android.content.SharedPreferences

class SharedPreferencesHelper(private val sharedPreferences: SharedPreferences) {

    var userName: String
        get() = sharedPreferences.getString(USER_NAME, "Unknown") ?: "Unknown"
        set(value) {
            sharedPreferences.edit().putString(USER_NAME, value).apply()
        }

    var userLastName: String
        get() = sharedPreferences.getString(USER_LAST_NAME, "Unknown") ?: "Unknown"
        set(value) {
            sharedPreferences.edit().putString(USER_LAST_NAME, value).apply()
        }

    companion object {

        //User
        const val USER_NAME = "userName"
        const val USER_LAST_NAME = "userLastName"

    }
}
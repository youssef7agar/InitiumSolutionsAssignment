package com.example.initiumsolutionsassignment.common

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.initiumsolutionsassignment.model.User
import com.google.gson.Gson

interface UserSettings : PreferencesHolder {
    var user: User
    var isLoggedIn: Boolean
}

class UserSettingsImp(private val context: Context, override val gson: Gson) : UserSettings {
    override var user: User by safeObjectPreference("user", User())

    override var isLoggedIn: Boolean = false
        get() = user.customerId != null

    override val prefs: Lazy<SharedPreferences> = lazy {
        with(context.applicationContext) {
            getSharedPreferences(
                "initium", MODE_PRIVATE
            )
        }
    }
}
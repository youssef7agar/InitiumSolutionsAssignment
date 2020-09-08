package com.example.initiumsolutionsassignment.common

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.initiumsolutionsassignment.model.User
import com.google.gson.Gson

interface UserSettings : PreferencesHolder {
    var user: User
    val loggedIn: Boolean
    fun logout()
}

class UserSettingsImp(private val context: Context, override val gson: Gson) : UserSettings {
    override var user: User by safeObjectPreference("user", User())
    override val loggedIn: Boolean
        get() = user.customerId != null

    override fun logout() {
        user = User()
    }

    override val prefs: Lazy<SharedPreferences> = lazy {
        with(context.applicationContext) {
            getSharedPreferences(
                "initium", MODE_PRIVATE
            )
        }
    }
}
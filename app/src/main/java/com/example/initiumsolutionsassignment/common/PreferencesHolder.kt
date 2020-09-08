package com.example.initiumsolutionsassignment.common

import android.content.SharedPreferences
import androidx.annotation.WorkerThread
import androidx.core.content.edit
import com.google.gson.Gson
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

interface PreferencesHolder {
    val prefs: Lazy<SharedPreferences>
    val gson: Gson
}

class NotNullObjectPreference<T>(
    private val key: String,
    private val default: T,
    private val type: Class<T>
) : ReadWriteProperty<PreferencesHolder, T> {

    @WorkerThread
    override fun getValue(thisRef: PreferencesHolder, property: KProperty<*>): T {
        return thisRef.gson.fromJson(
            thisRef.prefs.value.getString(key, thisRef.gson.toJson(default, type)),
            type
        )
    }

    override fun setValue(thisRef: PreferencesHolder, property: KProperty<*>, value: T) {
        thisRef.prefs.value.edit { putString(key, thisRef.gson.toJson(value, type)) }
    }
}

inline fun <reified T> safeObjectPreference(
    key: String,
    default: T
): ReadWriteProperty<PreferencesHolder, T> {
    return NotNullObjectPreference(key, default, T::class.java)
}
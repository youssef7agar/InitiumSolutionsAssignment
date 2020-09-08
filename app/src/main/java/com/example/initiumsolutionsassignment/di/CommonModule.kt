package com.example.initiumsolutionsassignment.di

import com.example.initiumsolutionsassignment.common.PreferencesHolder
import com.example.initiumsolutionsassignment.common.UserSettings
import com.example.initiumsolutionsassignment.common.UserSettingsImp
import com.google.gson.Gson
import org.koin.dsl.bind
import org.koin.dsl.module

val commonModule = module {
    single { Gson() }
    single { UserSettingsImp(get(), get()) } bind UserSettings::class bind PreferencesHolder::class
}
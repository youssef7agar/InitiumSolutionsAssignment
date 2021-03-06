package com.example.initiumsolutionsassignment.di

import com.example.initiumsolutionsassignment.common.UserSettingsImp
import com.example.initiumsolutionsassignment.repository.AuthRepo
import com.example.initiumsolutionsassignment.repository.MainRepo
import org.koin.dsl.module

val repoModule = module {

    factory { AuthRepo(get(), get(), get<UserSettingsImp>()) }
    factory { MainRepo(get()) }
}
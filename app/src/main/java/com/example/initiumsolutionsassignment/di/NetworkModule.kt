package com.example.initiumsolutionsassignment.di

import com.example.initiumsolutionsassignment.common.Constants
import com.example.initiumsolutionsassignment.network.createOkHttpClient
import com.example.initiumsolutionsassignment.network.createService
import com.example.initiumsolutionsassignment.network.moshi
import com.example.initiumsolutionsassignment.network.LogInApiService
import com.example.initiumsolutionsassignment.network.MainApiService
import com.example.initiumsolutionsassignment.network.RegisterApiService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single<Retrofit.Builder> {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
    }

    factory { createOkHttpClient() }
    single { moshi() }

    single {
        createService<MainApiService>(Constants.MAIN_BASE_URL, get(), moshi())
    }
    single {
        createService<LogInApiService>(Constants.LOGIN_BASE_URL, get(), moshi())
    }
    single {
        createService<RegisterApiService>(Constants.REGISTER_BASE_URL, get(), moshi())
    }

}
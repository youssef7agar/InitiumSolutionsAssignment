package com.example.initiumsolutionsassignment.di

import com.example.initiumsolutionsassignment.auth.login.LogInViewModel
import com.example.initiumsolutionsassignment.main.MainViewModel
import com.example.initiumsolutionsassignment.auth.register.RegisterViewModel
import com.example.initiumsolutionsassignment.main.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get(), get()) }
    viewModel { LogInViewModel(get()) }
    viewModel { RegisterViewModel(get(), get()) }
    viewModel { MainActivityViewModel(get()) }
}
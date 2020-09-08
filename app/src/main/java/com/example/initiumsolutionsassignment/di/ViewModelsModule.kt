package com.example.initiumsolutionsassignment.di

import com.example.initiumsolutionsassignment.login.LogInViewModel
import com.example.initiumsolutionsassignment.main.MainViewModel
import com.example.initiumsolutionsassignment.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { LogInViewModel() }
    viewModel { RegisterViewModel() }
}
package com.example.initiumsolutionsassignment.di

import com.example.initiumsolutionsassignment.auth.login.LogInUseCase
import com.example.initiumsolutionsassignment.main.MainUseCase
import com.example.initiumsolutionsassignment.auth.register.RegisterUseCase
import org.koin.dsl.module

val useCasesModule = module {
    factory { MainUseCase(get()) }
    factory { LogInUseCase(get()) }
    factory { RegisterUseCase(get()) }
}
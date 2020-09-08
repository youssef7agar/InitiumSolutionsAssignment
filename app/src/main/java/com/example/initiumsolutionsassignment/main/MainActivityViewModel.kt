package com.example.initiumsolutionsassignment.main

import androidx.lifecycle.ViewModel
import com.example.initiumsolutionsassignment.auth.CacheUserUseCase
import com.example.initiumsolutionsassignment.model.User

class MainActivityViewModel(private val cacheUserUseCase: CacheUserUseCase): ViewModel() {

    fun getCachedUser(): User? {
        return cacheUserUseCase.getCachedUser()
    }

    fun logOut(){
        cacheUserUseCase.logout()
    }
}
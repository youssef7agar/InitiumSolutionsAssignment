package com.example.initiumsolutionsassignment.auth

import com.example.initiumsolutionsassignment.model.User
import com.example.initiumsolutionsassignment.repository.AuthRepo

class CacheUserUseCase(private val authRepo: AuthRepo) {
    fun cacheUser(user: User){
        authRepo.cacheUser(user)
    }

    fun getCachedUser(): User?{
        return authRepo.getCachedUser()
    }

    fun logout(){
        authRepo.logOut()
    }
}
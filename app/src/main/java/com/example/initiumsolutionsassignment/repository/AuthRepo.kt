package com.example.initiumsolutionsassignment.repository

import com.example.initiumsolutionsassignment.common.UserSettings
import com.example.initiumsolutionsassignment.model.*
import com.example.initiumsolutionsassignment.network.LogInApiService
import com.example.initiumsolutionsassignment.network.RegisterApiService
import java.lang.Exception

class AuthRepo(private val logInApiService: LogInApiService, private val registerApiService: RegisterApiService, private val userSettings: UserSettings) {

    suspend fun logIn(logInRequest: LogInRequest): Result<LogInResponse>{
        return try {
            Result.Success(logInApiService.logIn(logInRequest))
        }catch (e: Exception){
            Result.Error(e)
        }
    }

    suspend fun register(registerRequest: RegisterRequest): Result<RegisterResponse>{
        return try {
            Result.Success(registerApiService.register(registerRequest))
        }catch (e: Exception){
            Result.Error(e)
        }
    }

    fun cacheUser(user: User): User{
        userSettings.user = user
        return userSettings.user
    }

    fun logOut(){
        userSettings.logout()
    }

    fun getCachedUser(): User?{
        return if (userSettings.user.customerId.isNullOrEmpty()){
            null
        }else{
            userSettings.user
        }
    }
}
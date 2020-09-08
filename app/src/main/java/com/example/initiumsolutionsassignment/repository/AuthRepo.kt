package com.example.initiumsolutionsassignment.repository

import com.example.initiumsolutionsassignment.common.UserSettings
import com.example.initiumsolutionsassignment.model.LogInRequest
import com.example.initiumsolutionsassignment.model.LogInResponse
import com.example.initiumsolutionsassignment.model.RegisterRequest
import com.example.initiumsolutionsassignment.model.RegisterResponse
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
}
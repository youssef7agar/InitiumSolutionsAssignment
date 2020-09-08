package com.example.initiumsolutionsassignment.network

import com.example.initiumsolutionsassignment.common.Constants
import com.example.initiumsolutionsassignment.model.LogInRequest
import com.example.initiumsolutionsassignment.model.LogInResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LogInApiService {

    @POST(Constants.LOGIN_URL)
    suspend fun logIn(@Body logInRequest: LogInRequest): LogInResponse
}
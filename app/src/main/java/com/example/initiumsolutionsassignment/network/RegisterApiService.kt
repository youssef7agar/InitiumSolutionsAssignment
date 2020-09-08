package com.example.initiumsolutionsassignment.network

import com.example.initiumsolutionsassignment.common.Constants
import com.example.initiumsolutionsassignment.model.RegisterRequest
import com.example.initiumsolutionsassignment.model.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterApiService {

    @POST(Constants.REGISTER_URL)
    suspend fun register(@Body registerRequest: RegisterRequest): RegisterResponse
}
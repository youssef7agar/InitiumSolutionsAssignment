package com.example.initiumsolutionsassignment.network

import com.example.initiumsolutionsassignment.common.Constants
import com.example.initiumsolutionsassignment.model.MainResponse
import retrofit2.http.POST

interface MainApiService {

    @POST(Constants.MAIN_URL)
    suspend fun getMain(): MainResponse
}
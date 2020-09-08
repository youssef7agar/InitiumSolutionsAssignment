package com.example.initiumsolutionsassignment.repository

import com.example.initiumsolutionsassignment.model.MainResponse
import com.example.initiumsolutionsassignment.network.MainApiService
import java.lang.Exception

class MainRepo(private val mainApiService: MainApiService) {

    suspend fun getMain(): Result<MainResponse>{
        return try {
            Result.Success(mainApiService.getMain())
        }catch (e: Exception){
            Result.Error(e)
        }
    }
}
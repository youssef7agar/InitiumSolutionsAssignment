package com.example.initiumsolutionsassignment.model

import com.squareup.moshi.Json

data class LogInRequest(
    @Json (name= "CustomerEmail")
    val customerEmail: String,
    @Json (name= "CustomerPassword")
    val customerPassword: String
)
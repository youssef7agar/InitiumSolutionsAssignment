package com.example.initiumsolutionsassignment.model

import com.squareup.moshi.Json

data class RegisterResponse(
    @Json (name= "CustomerID")
    val customerID: String,
    @Json (name= "Err_Flag")
    val errFlag: Boolean,
    @Json (name= "Err_Desc")
    val errDesc: String
)
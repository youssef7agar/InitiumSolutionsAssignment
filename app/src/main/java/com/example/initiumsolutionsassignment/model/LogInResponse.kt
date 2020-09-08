package com.example.initiumsolutionsassignment.model

import com.squareup.moshi.Json

data class LogInResponse(
    @Json (name= "CustomerID")
    val customerID: String,
    @Json (name= "FirstName")
    val firstName: String,
    @Json (name= "LastName")
    val lastName: String,
    @Json (name= "Err_Flag")
    val errFlag: Boolean,
    @Json (name= "Err_Desc")
    val errDesc: String
)
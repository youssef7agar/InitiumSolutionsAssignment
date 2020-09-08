package com.example.initiumsolutionsassignment.model

import com.squareup.moshi.Json

data class RegisterRequest(
    @Json (name= "CustomerFirstName")
    val customerFirstName: String,
    @Json (name= "CustomerLastName")
    val customerLastName: String,
    @Json (name= "CustomerMobileNo")
    val customerMobileNo: String,
    @Json (name= "CustomerCivilID")
    val customerCivilID: String,
    @Json (name= "CustomerEmail")
    val customerEmail: String,
    @Json (name= "CustomerPassword")
    val customerPassword: String
)
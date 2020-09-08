package com.example.initiumsolutionsassignment.register

import com.example.initiumsolutionsassignment.model.RegisterResponse
import java.lang.Exception

data class RegisterViewState(
    val loading: Boolean = false,
    val error: Exception? = null,
    val registerResponse: RegisterResponse? = null
)
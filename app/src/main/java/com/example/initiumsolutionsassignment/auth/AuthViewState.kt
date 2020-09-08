package com.example.initiumsolutionsassignment.auth

import com.example.initiumsolutionsassignment.model.LogInResponse
import java.lang.Exception

data class AuthViewState(
    val loading: Boolean = false,
    val error: Exception? = null,
    val logInResponse: LogInResponse? = null
) {
}
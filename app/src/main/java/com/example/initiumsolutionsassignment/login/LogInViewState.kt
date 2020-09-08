package com.example.initiumsolutionsassignment.login

import com.example.initiumsolutionsassignment.model.LogInResponse
import java.lang.Exception

data class LogInViewState(
    val loading: Boolean = false,
    val error: Exception? = null,
    val logInResponse: LogInResponse? = null
) {
}
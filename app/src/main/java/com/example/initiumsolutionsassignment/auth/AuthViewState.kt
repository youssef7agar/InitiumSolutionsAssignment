package com.example.initiumsolutionsassignment.auth

import com.example.initiumsolutionsassignment.model.User

data class AuthViewState(
    val loading: Boolean = false,
    val error: Exception? = null,
    val user: User?=null
)
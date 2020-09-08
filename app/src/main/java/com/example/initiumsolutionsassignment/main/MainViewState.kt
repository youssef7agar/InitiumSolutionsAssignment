package com.example.initiumsolutionsassignment.main

import com.example.initiumsolutionsassignment.model.MainResponse
import java.lang.Exception

data class MainViewState(
    val loading: Boolean = false,
    val error: Exception? = null,
    val entities: List<MainResponse.Entity>? = null
)
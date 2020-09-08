package com.example.initiumsolutionsassignment.main

import com.example.initiumsolutionsassignment.repository.MainRepo
import com.example.initiumsolutionsassignment.repository.unwrapResult

class MainUseCase(private val repo: MainRepo) {
    suspend operator fun invoke(): MainViewState {
        return repo.getMain().unwrapResult({
            MainViewState(entities = it.collection.toList(), loading = false)
        }, {
            MainViewState(error = it, loading = false)
        })
    }
}
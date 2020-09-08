package com.example.initiumsolutionsassignment.login

import com.example.initiumsolutionsassignment.model.LogInRequest
import com.example.initiumsolutionsassignment.model.LogInResponse
import com.example.initiumsolutionsassignment.repository.AuthRepo
import com.example.initiumsolutionsassignment.repository.unwrapResult

class LogInUseCase(private val repo: AuthRepo) {
    suspend operator fun invoke(logInRequest: LogInRequest): LogInViewState {
        return repo.logIn(logInRequest).unwrapResult({
            LogInViewState(logInResponse = it)
        }, {
            LogInViewState(error = it)
        })
    }
}
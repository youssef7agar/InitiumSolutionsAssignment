package com.example.initiumsolutionsassignment.auth.login

import com.example.initiumsolutionsassignment.auth.AuthViewState
import com.example.initiumsolutionsassignment.model.LogInRequest
import com.example.initiumsolutionsassignment.repository.AuthRepo
import com.example.initiumsolutionsassignment.repository.unwrapResult

class LogInUseCase(private val repo: AuthRepo) {
    suspend operator fun invoke(logInRequest: LogInRequest): AuthViewState {
        return repo.logIn(logInRequest).unwrapResult({
            AuthViewState(
                logInResponse = it
            )
        }, {
            AuthViewState(error = it)
        })
    }
}
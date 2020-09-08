package com.example.initiumsolutionsassignment.auth.login

import com.example.initiumsolutionsassignment.auth.AuthViewState
import com.example.initiumsolutionsassignment.model.LogInRequest
import com.example.initiumsolutionsassignment.model.User
import com.example.initiumsolutionsassignment.repository.AuthRepo
import com.example.initiumsolutionsassignment.repository.unwrapResult

class LogInUseCase(private val repo: AuthRepo) {
    suspend operator fun invoke(logInRequest: LogInRequest, rememberMe: Boolean): AuthViewState {
        return repo.logIn(logInRequest).unwrapResult({
            val user = User(it.customerID, it.firstName, it.lastName)
            if (rememberMe)
                repo.cacheUser(user)
            AuthViewState(user = user)
        }, {
            AuthViewState(error = it)
        })
    }
}
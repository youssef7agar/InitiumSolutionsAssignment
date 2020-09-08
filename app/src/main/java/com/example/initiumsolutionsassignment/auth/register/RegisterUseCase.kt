package com.example.initiumsolutionsassignment.auth.register

import com.example.initiumsolutionsassignment.auth.AuthViewState
import com.example.initiumsolutionsassignment.model.LogInRequest
import com.example.initiumsolutionsassignment.model.RegisterRequest
import com.example.initiumsolutionsassignment.repository.AuthRepo
import com.example.initiumsolutionsassignment.repository.unwrapResult
import com.example.initiumsolutionsassignment.repository.unwrapSuspendResult

class RegisterUseCase(private val repo: AuthRepo) {
    suspend operator fun invoke(registerRequest: RegisterRequest): AuthViewState {
        return repo.register(registerRequest).unwrapSuspendResult({
            repo.logIn(LogInRequest(customerEmail = registerRequest.customerEmail, customerPassword = registerRequest.customerPassword)).unwrapResult({
                AuthViewState(
                    logInResponse = it
                )
            },{
                AuthViewState(error = it)
            })
        }, {
            AuthViewState(error = it)
        })
    }
}
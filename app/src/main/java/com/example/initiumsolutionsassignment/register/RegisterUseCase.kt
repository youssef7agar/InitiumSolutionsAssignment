package com.example.initiumsolutionsassignment.register

import com.example.initiumsolutionsassignment.model.RegisterRequest
import com.example.initiumsolutionsassignment.repository.AuthRepo
import com.example.initiumsolutionsassignment.repository.unwrapResult

class RegisterUseCase(private val repo: AuthRepo) {
    suspend operator fun invoke(registerRequest: RegisterRequest): RegisterViewState {
        return repo.register(registerRequest).unwrapResult({
            RegisterViewState(registerResponse = it)
        }, {
            RegisterViewState(error = it)
        })
    }
}
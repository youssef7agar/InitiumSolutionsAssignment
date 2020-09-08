package com.example.initiumsolutionsassignment.auth.register

import androidx.lifecycle.MutableLiveData
import com.example.initiumsolutionsassignment.auth.AuthViewState
import com.example.initiumsolutionsassignment.common.BaseViewModel
import com.example.initiumsolutionsassignment.model.RegisterRequest
import kotlinx.coroutines.launch

class RegisterViewModel(private val registerUseCase: RegisterUseCase): BaseViewModel<AuthViewState>() {
    override val _viewState = MutableLiveData<AuthViewState>().apply { AuthViewState() }

    fun register(registerRequest: RegisterRequest) = launch{
        setState(AuthViewState()) { copy(loading = true)}
        postState(registerUseCase(registerRequest))
    }

    private fun postState(state: AuthViewState?) {
        _viewState.postValue(state)
    }
}
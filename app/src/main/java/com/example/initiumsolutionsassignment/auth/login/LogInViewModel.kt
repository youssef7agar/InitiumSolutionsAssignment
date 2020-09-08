package com.example.initiumsolutionsassignment.auth.login

import androidx.lifecycle.MutableLiveData
import com.example.initiumsolutionsassignment.auth.AuthViewState
import com.example.initiumsolutionsassignment.common.BaseViewModel
import com.example.initiumsolutionsassignment.model.LogInRequest
import kotlinx.coroutines.launch

class LogInViewModel(private val logInUseCase: LogInUseCase): BaseViewModel<AuthViewState>() {
    override val _viewState = MutableLiveData<AuthViewState>().apply { AuthViewState() }

    fun logIn(logInRequest: LogInRequest, rememberMe: Boolean) = launch{
        setState(AuthViewState()) { copy(loading = true)}
        postState(logInUseCase(logInRequest, rememberMe))
    }

    private fun postState(state: AuthViewState?){
        _viewState.postValue(state)
    }
}
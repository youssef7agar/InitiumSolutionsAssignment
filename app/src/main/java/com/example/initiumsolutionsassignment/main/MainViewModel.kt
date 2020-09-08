package com.example.initiumsolutionsassignment.main

import androidx.lifecycle.MutableLiveData
import com.example.initiumsolutionsassignment.auth.CacheUserUseCase
import com.example.initiumsolutionsassignment.common.BaseViewModel
import com.example.initiumsolutionsassignment.model.User
import kotlinx.coroutines.launch

class MainViewModel(private val mainUseCase: MainUseCase, private val cacheUserUseCase: CacheUserUseCase) : BaseViewModel<MainViewState>() {

    override val _viewState = MutableLiveData<MainViewState>().apply { MainViewState() }

    init {
        getMain()
    }

    private fun getMain() = launch {
        setState(MainViewState()) { copy(loading = true) }
        postState(mainUseCase())
    }

    fun getCachedUser(): User?{
        return cacheUserUseCase.getCachedUser()
    }

    private fun postState(state: MainViewState?) {
        _viewState.postValue(state)
    }
}
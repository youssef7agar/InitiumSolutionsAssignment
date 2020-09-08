package com.example.initiumsolutionsassignment.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.initiumsolutionsassignment.common.BaseViewModel
import kotlinx.coroutines.launch

class MainViewModel(private val mainUseCase: MainUseCase) : BaseViewModel<MainViewState>() {

    override val _viewState = MutableLiveData<MainViewState>().apply { MainViewState() }

    init {
        getMain()
    }

    private fun getMain() = launch {
        setState(MainViewState()) { copy(loading = true) }
        postState(mainUseCase())
    }

    private fun postState(state: MainViewState?) {
        _viewState.postValue(state)
    }
}
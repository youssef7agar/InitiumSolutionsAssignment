package com.example.initiumsolutionsassignment.main

import androidx.lifecycle.MutableLiveData
import com.example.initiumsolutionsassignment.common.BaseViewModel
import kotlinx.coroutines.launch

class MainViewModel(private val mainUseCase: MainUseCase) : BaseViewModel<MainViewState>() {

    override val _viewState = MutableLiveData<MainViewState>()

    fun getMain() = launch {
        postState(viewStateValue().copy(loading = true))
        postState(mainUseCase())
    }

    private fun viewStateValue() = _viewState.value!!
    private fun postState(state: MainViewState){
        _viewState.postValue(state)
    }
}
package com.example.initiumsolutionsassignment.common

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

abstract class BaseViewModel<T> : ViewModel(), CoroutineScope {

    private val job = SupervisorJob()

    override val coroutineContext = Dispatchers.IO + job

    protected abstract val _viewState: MutableLiveData<T>

    val viewState: LiveData<T> get() = _viewState

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
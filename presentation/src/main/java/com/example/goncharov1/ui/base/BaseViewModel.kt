package com.example.goncharov1.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    private val _screenState = MutableStateFlow<ScreenState>(ScreenState.None())
    val screenState = _screenState.asStateFlow()

    protected fun success() = viewModelScope.launch {
        _screenState.emit(ScreenState.Success())
    }

    protected fun sendError(error: String?) = viewModelScope.launch {
        _screenState.emit(ScreenState.Error(messageError = error))
    }

    protected fun loading() = viewModelScope.launch {
        _screenState.emit(ScreenState.Loading())
    }

    protected fun none() = viewModelScope.launch {
        _screenState.emit(ScreenState.None())
    }
}
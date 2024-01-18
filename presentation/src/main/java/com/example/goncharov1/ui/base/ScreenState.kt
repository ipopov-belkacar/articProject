package com.example.goncharov1.ui.base

sealed class ScreenState(val message: String? = null) {
    class Error(messageError: String?) : ScreenState(messageError)
    class Loading : ScreenState()
    class None : ScreenState()
}
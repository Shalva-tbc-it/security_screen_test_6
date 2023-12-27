package com.example.securityscreen.data

data class PinCodeViewState(
    val isLoading: Boolean = false,
    val pinError: Boolean = false
)

sealed class PinCodeIntent {
    data class SubmitPin(val pin: String) : PinCodeIntent()
}
package com.example.securityscreen.presentation.security_screen

import androidx.lifecycle.ViewModel
import com.example.securityscreen.data.PinCodeIntent
import com.example.securityscreen.data.PinCodeViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class PinCodeViewModel : ViewModel() {
    private val _state = MutableStateFlow(PinCodeViewState())
    val state: StateFlow<PinCodeViewState> = _state

    fun processIntent(intent: PinCodeIntent) {
        when (intent) {
            is PinCodeIntent.SubmitPin -> {
                // pin validate
                val isValidPin = validatePin(intent.pin)

                if (isValidPin) {
                    _state.value = PinCodeViewState(pinError = false)
                    // pinCodeView.navigateToNextScreen()
                } else {
                    _state.value = PinCodeViewState(pinError = true)
                    // pinCodeView.showPinError()
                }
            }
        }
    }

    private fun validatePin(pin: String): Boolean {
        // pin validation logic
        return pin.length == 4
    }
}

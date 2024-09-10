package com.example.composebasics.effectHandlers.launched_effects

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LaunchedEffectViewModel : ViewModel() {

    private val _sharedFlow = MutableSharedFlow<LoginState>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            _sharedFlow.emit(LoginState.ShowSnackBar("Show"))
        }
    }

    sealed class LoginState {
        data class ShowSnackBar(val msg: String) : LoginState()
        data class Navigation(val route: String) : LoginState()
    }
}
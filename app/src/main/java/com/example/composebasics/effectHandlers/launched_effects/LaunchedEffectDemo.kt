package com.example.composebasics.effectHandlers.launched_effects

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.collectLatest


@Composable
fun LaunchedEffectDemo(viewModel: LaunchedEffectViewModel) {
    LaunchedEffect(key1 = true) {
        viewModel.sharedFlow.collect { event ->
            when (event) {
                is LaunchedEffectViewModel.LoginState.ShowSnackBar -> {

                }

                is LaunchedEffectViewModel.LoginState.Navigation -> {
                    
                }
            }
        }
    }
}
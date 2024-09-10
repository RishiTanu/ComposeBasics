package com.example.composebasics.effectHandlers.remember_update_state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import kotlinx.coroutines.delay


@Composable
fun RememberUpdatedStateDemo(
    onTimeOut: () -> Unit,
) {
    val updatedTimeOut by rememberUpdatedState(newValue = onTimeOut)
    LaunchedEffect(key1 = true) {
        delay(2500)
        updatedTimeOut()
    }
}
package com.example.composebasics.effectHandlers.remember_coroutine_scope

import android.util.Log
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun RememberCoroutineScope() {
    val scope = rememberCoroutineScope()

    Button(onClick = {
        scope.launch {
            delay(1000)
            Log.d("TAG", "RememberCoroutineScope: Compose")
        }
    }) {
    }
}
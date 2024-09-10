package com.example.composebasics.effectHandlers.side_effects

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect


@Composable
fun SideEffectDemo(nonComposeCounter: Int) {
    SideEffect {
        Log.d("TAG", "SideEffectDemo: call every successfully recomposition")
    }
}
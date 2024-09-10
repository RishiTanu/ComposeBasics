package com.example.composebasics.effectHandlers.produce_state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import kotlinx.coroutines.delay

@Composable
fun ProduceStateDemo(counterTo: Int): State<Int> {
    return produceState(initialValue = 0) {
        while (value < counterTo) {
            delay(1000L)
            value++
        }
    }
}
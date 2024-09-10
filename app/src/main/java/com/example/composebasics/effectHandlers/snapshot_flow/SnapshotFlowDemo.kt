package com.example.composebasics.effectHandlers.snapshot_flow

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapNotNull

@Composable
fun SnapshotFlowDemo() {
    LaunchedEffect(key1 = true) {
        snapshotFlow {}
            .mapNotNull { }
            .distinctUntilChanged()
            .collect { msg ->

            }
    }
}
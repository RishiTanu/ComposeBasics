package com.example.composebasics.permission_handling

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionHandle() {

    Surface(modifier = Modifier.fillMaxSize()) {
        val permissionState = rememberMultiplePermissionsState(
            permissions = listOf(
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
            )
        )

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            val lifeCycleOwner = LocalLifecycleOwner.current
            DisposableEffect(key1 = lifeCycleOwner, effect = {
                val observer = LifecycleEventObserver { _, event ->
                    if (event == Lifecycle.Event.ON_START) {
                        permissionState.launchMultiplePermissionRequest()
                    }
                }
                lifeCycleOwner.lifecycle.addObserver(observer)
                onDispose {
                    lifeCycleOwner.lifecycle.removeObserver(observer)
                }
              }
            )

            permissionState.permissions.forEach { prem ->
                when (prem.permission) {
                    Manifest.permission.CAMERA -> {
                        when {
                            prem.hasPermission -> {
                                Text(text = "Camera Position accepted")
                            }

                            prem.shouldShowRationale -> {
                                Text(text = "Camera Position needed to access the camera")
                            }

                            prem.isPermanentPermissionDenied() -> {
                                Text(text = "Camera Permission was permanently denied you need to open it from settings")
                            }
                        }
                    }

                    Manifest.permission.RECORD_AUDIO -> {
                        when {
                            prem.hasPermission -> {
                                Text(text = "Record Position accepted")
                            }

                            prem.shouldShowRationale -> {
                                Text(text = "Record Position needed to access the camera")
                            }

                            prem.isPermanentPermissionDenied() -> {
                                Text(text = "Record Permission was permanently denied you need to open it from settings")
                            }
                        }
                    }
                }
            }
        }
    }
}
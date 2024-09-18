package com.example.composebasics.permission_handling

import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState

@OptIn(ExperimentalPermissionsApi::class)
fun PermissionState.isPermanentPermissionDenied() : Boolean{
    return !shouldShowRationale && !hasPermission
}
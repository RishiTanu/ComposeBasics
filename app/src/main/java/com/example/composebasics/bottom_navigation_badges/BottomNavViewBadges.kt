package com.example.composebasics.bottom_navigation_badges

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

@Composable
fun BottomNavViewBadges() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationWithBadges(
                items = listOf(
                    BottomNavItem(
                        name = "Home",
                        route = "Home",
                        icon = Icons.Default.Home,
                    ),
                    BottomNavItem(
                        name = "Chat",
                        route = "Chat",
                        icon = Icons.Default.Notifications,
                        badgeCount = 100,
                    ),
                    BottomNavItem(
                        name = "Setting",
                        route = "Setting",
                        icon = Icons.Default.Settings,
                    ),
                ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route)
                },
            )
        },
    ) { paddingValues->
        Navigation(navHostController = navController)
    }
}
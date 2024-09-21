package com.example.composebasics.deep_link

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink


@Composable
fun DeepLink() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Home") {
        composable(route = "home") {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Button(onClick = {
                    navController.navigate("detail")
                }) {
                    Text(text = "Open Detail")
                }
            }
        }
        composable(
            route = "detail",
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "https://pl-coding.com/{id}"
                    action = Intent.ACTION_VIEW
                }
            ),
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )

        ) { entry ->
            val id = entry.arguments?.getInt("id")
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Test $id")
            }
        }
    }
}
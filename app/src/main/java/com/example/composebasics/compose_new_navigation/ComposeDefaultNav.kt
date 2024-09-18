package com.example.composebasics.compose_new_navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ComposeDefaultNav() {
    val rememberNavController = rememberNavController()
    NavHost(navController = rememberNavController, startDestination = "login") {
        composable(route = "login") {
            LoginScreen(navController = rememberNavController)
        }
        composable(
            route = "profile/{name}/{userId}/{timestemp}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                },
                navArgument("userId") {
                    type = NavType.StringType
                },
                navArgument("timestemp") {
                    type = NavType.LongType
                },
            )
        ) {
            val name = it.arguments?.getString("name")!!
            val userId = it.arguments?.getString("userId")!!
            val times = it.arguments?.getLong("timestemp")!!

            ProfileScreen(
                navController = rememberNavController,
                name = name,
                userId = userId,
                created = times
            )
        }

        composable(
            "post/{showOnlyPostByUser}",
            arguments = listOf(
                navArgument("showOnlyPostsByUser") {
                    type = NavType.BoolType
                    defaultValue = false
                }
            )
        ) {
            val showOnlyPostsByUser = it.arguments?.getBoolean("showOnlyPostsByUser") ?: false
            PostScreen(showOnlyPostsByUser)
        }
    }
}


@Composable
fun LoginScreen(
    navController: NavController,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Login Screen")
        Button(
            onClick = {
                navController.navigate("profile/rishikesh/userid/123456789")
            }
        ) {
            Text(text = "Go to Profile Screen")
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProfileScreen(
    navController: NavController,
    name: String,
    userId: String,
    created: Long,
) {
    val user = remember {
        User(
            name = name,
            id = userId,
            created = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(created), ZoneId.systemDefault()
            )
        )
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Profile Screen $user", textAlign = TextAlign.Center)
        Button(onClick = {
            navController.navigate("post/true")
        }) {
            Text(text = "Go to Post Screen")
        }
    }
}

@Composable
fun PostScreen(
    showOnlyPostByUser: Boolean = false,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Post Screen, $showOnlyPostByUser")
    }
}
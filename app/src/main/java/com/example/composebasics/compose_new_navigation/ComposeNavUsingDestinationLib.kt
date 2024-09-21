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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.composebasics.compose_new_navigation.destinations.PostNewScreenDestination
import com.example.composebasics.compose_new_navigation.destinations.ProfileNewScreenDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.time.LocalDateTime


@Composable
fun ComposeNavUsingDestinationLib(modifier: Modifier = Modifier) {
    DestinationsNavHost(navGraph = NavGraphs.root)
}

@RequiresApi(Build.VERSION_CODES.O)
@Destination(start = true)
@Composable
fun LoginNewScreen(
    navigator: DestinationsNavigator,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Login Screen")
        Button(
            onClick = {
                //    ProfileNewDes
                navigator.navigate(
                    ProfileNewScreenDestination(
                        user = User(
                            name = "Rishikesh",
                            id = "123",
                            created = LocalDateTime.now()
                        )
                    )
                )
            }
        ) {
            Text(text = "Go to Profile Screen")
        }
    }
}

@Destination
@Composable
fun ProfileNewScreen(
    navigator: DestinationsNavigator,
    user: User,
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Profile Screen $user", textAlign = TextAlign.Center)
        Button(onClick = {
            navigator.navigate(
                PostNewScreenDestination(
                    showOnlyPostByUser = true
                )
            )
        }) {
            Text(text = "Go to Post Screen")
        }
    }
}

@Destination
@Composable
fun PostNewScreen(
    showOnlyPostByUser: Boolean = false,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Post Screen, $showOnlyPostByUser")
    }
}
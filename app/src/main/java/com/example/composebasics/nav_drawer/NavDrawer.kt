package com.example.composebasics.nav_drawer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebasics.R
import kotlinx.coroutines.launch

@Composable
fun NavDrawer() {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        topBar = {
            TopAppBar(
                onNavigationIconCLick = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        modifier = Modifier.fillMaxSize(),
        drawerContent =
        {
            DrawerHeader()
            DrawerBody(
                items = listOf(
                    MenuItem(
                        id = "Home",
                        title = "Home",
                        contentDes = "Go to Home",
                        icon = Icons.Default.Home
                    ),
                    MenuItem(
                        id = "Account",
                        title = "Account",
                        contentDes = "Go to Home",
                        icon = Icons.Default.AccountBox
                    ),
                    MenuItem(
                        id = "Profile",
                        title = "Profile",
                        contentDes = "Go to Home",
                        icon = Icons.Default.Person
                    )
                ),
                onItemClick = {

                }
            )
        }) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {

        }
    }

}

@Composable
fun DrawerHeader(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(64.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Header", fontSize = 64.sp)
    }
}

@Composable
fun DrawerBody(
    items: List<MenuItem>,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    modifier: Modifier = Modifier,
    onItemClick: (MenuItem) -> Unit,
) {
    LazyColumn(modifier) {
        items(items) { item ->
            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onItemClick(item)
                }
                .padding(16.dp)
            ) {
                Icon(imageVector = item.icon, contentDescription = item.contentDes)
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = item.title,
                    style = itemTextStyle,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}


@Composable
fun TopAppBar(
    onNavigationIconCLick: () -> Unit,
) {
    androidx.compose.material.TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        backgroundColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        navigationIcon = {
            IconButton(onClick = { onNavigationIconCLick }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "")
            }
        }
    )
}
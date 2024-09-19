package com.example.composebasics.all_screen_size_support

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun AllScreenSizeSupport(

) {
    val windowInfo = rememberWindowInfo()
    if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compat) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(10) { index ->
                Text(
                    text = "Items $index",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Color.Cyan
                        )
                        .padding(16.dp)
                )
            }
            items(5) { index ->
                Text(
                    text = "Items $index",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Color.Yellow
                        )
                        .padding(16.dp)
                )
            }
        }
    }else{
        Row(modifier = Modifier.fillMaxWidth()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize().weight(1f)
            ) {
                items(10) { index ->
                    Text(
                        text = "Items $index",
                        fontSize = 16.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Color.Cyan
                            )
                            .padding(16.dp)
                    )
                }
            }
            LazyColumn(
                modifier = Modifier.fillMaxSize().weight(1f)
            ) {
                items(10) { index ->
                    Text(
                        text = "Items $index",
                        fontSize = 16.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Color.Green
                            )
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}
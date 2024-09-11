package com.example.composebasics.insta_profile

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

data class ImageWithText(
    val image : Painter,
    val text:String
)

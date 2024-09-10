package com.example.composebasics.meditation

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class Feature(
    val title: String,
    @DrawableRes val iconId: Color,
    val lightColor: Color,
    val darkColor: Color,
)

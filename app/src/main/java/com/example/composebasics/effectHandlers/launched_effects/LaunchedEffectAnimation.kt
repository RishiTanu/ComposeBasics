package com.example.composebasics.effectHandlers.launched_effects

import androidx.compose.animation.core.Animatable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

@Composable
fun LaunchedEffectAnimation(
    counter: Int,
) {
    val animate = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = counter) {
        animate.animateTo(counter.toFloat())
    }

}
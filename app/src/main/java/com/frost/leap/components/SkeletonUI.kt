package com.frost.leap.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.frost.leap.ui.theme.AceOnlineTheme

@Composable
fun SkeletonUI(
    skeletonColor: Color = Color.LightGray,
    highlightColor: Color = Color.Gray,
    highlightWidth: Dp = 120.dp,
    highlightHeight: Dp = 120.dp,
    animationDuration: Int = 1000
) {
    var shimmerTranslate by remember { mutableStateOf(0f) }

    val transition = rememberInfiniteTransition(label = "")
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = animationDuration, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    LaunchedEffect(Unit) {
        shimmerTranslate = translateAnim
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Placeholder content or loading state
        // ...

        // Skeleton UI effect
        Canvas(modifier = Modifier.fillMaxWidth().requiredHeight(50.dp)) {
            val gradientShader = Brush.linearGradient(
                colors = listOf(skeletonColor, highlightColor, skeletonColor),
                start = Offset(0f, 0f),
                end = Offset(shimmerTranslate, 0f),
                tileMode = TileMode.Clamp
            )

            drawRect(
                brush = gradientShader,
                size = Size(size.width, highlightHeight.value)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CustomSkeleton() {
    AceOnlineTheme {
        SkeletonUI()
    }
}
package com.frost.leap.components

import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner

@Composable
fun BackHandler(
    enabled: Boolean = true,
    onBackPressed: () -> Unit
) {
    val backCallback = remember {
        object : OnBackPressedCallback(enabled) {
            override fun handleOnBackPressed() {
                onBackPressed()
            }
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    val backDispatcherOwner = LocalOnBackPressedDispatcherOwner.current

    DisposableEffect(backDispatcherOwner, enabled) {
        backCallback.isEnabled = enabled
        backDispatcherOwner?.onBackPressedDispatcher?.addCallback(backCallback)

        onDispose {
            backCallback.remove()
        }
    }
}
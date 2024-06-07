package com.frost.leap.routing

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.frost.leap.architecture.onboarding.presentation.OnBoardingEvent
import com.frost.leap.architecture.onboarding.presentation.OnBoardingStateHolder

@OptIn(ExperimentalAnimationApi::class)
data class ComposableConfigModel(
    val route: String,
    val enterTransition: (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition)? = null,
    val popExitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition)? = null,
    val popEnterTransition: (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition)? = null,
    val exitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition)? = null,
//    val composableContent: @Composable (NavBackStackEntry,NavController, OnBoardingStateHolder?, (OnBoardingEvent) -> Unit, () -> Unit) -> Unit = { _, _, _, _ -> }
    val composableContent: @Composable (NavBackStackEntry, NavController) -> Unit


)

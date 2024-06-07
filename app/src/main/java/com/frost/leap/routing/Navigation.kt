package com.frost.leap.routing

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation


@ExperimentalAnimationApi
@Composable
fun Navigation(navController: NavHostController) {

    val onBoardingComposables = ComposableConfigImpl.getOnBoardingComposable(navController)
    val mainFeatureComposables = ComposableConfigImpl.getMainFeatureComposable(navController)
    val splashScreenComposables = ComposableConfigImpl.getSplashScreenComposable(navController)

    AnimatedNavHost(navController, startDestination = Screen.SplashScreen.route) {


        // setting up splash screen composables ...
        composable(
            route = splashScreenComposables.route,
            exitTransition = splashScreenComposables.exitTransition,
            enterTransition = splashScreenComposables.enterTransition,
            popEnterTransition = splashScreenComposables.popEnterTransition,
            popExitTransition = splashScreenComposables.popExitTransition
        )
        { navBackStackEntry ->
            splashScreenComposables.composableContent(navBackStackEntry, navController)
        }


        // setting up OnBoarding Composables ....
        navigation(
            startDestination = Screen.OnBoardingPage.route,
            route = "onboarding"
        ) {
            onBoardingComposables.forEach { config ->
                composable(
                    route = config.route,
                    enterTransition = config.enterTransition,
                    exitTransition = config.exitTransition,
                    popEnterTransition = config.popEnterTransition,
                    popExitTransition = config.popExitTransition
                ) { navBackStackEntry ->
                    config.composableContent(navBackStackEntry, navController)
                }


            }
        }

        //OTP Page
        navigation(
            startDestination = Screen.OTPPage.route,
            route = "opt"
        ) {
            onBoardingComposables.forEach { config ->
                composable(
                    route = config.route,
                    enterTransition = config.enterTransition,
                    exitTransition = config.exitTransition,
                    popEnterTransition = config.popEnterTransition,
                    popExitTransition = config.popExitTransition
                ) { navBackStackEntry ->
                    config.composableContent(navBackStackEntry, navController)
                }


            }
        }

        // setting up main feature composables ...
        navigation(
            startDestination = Screen.VideoPlayerPage.route,
            route = "main_feature"
        ) {
            mainFeatureComposables.forEach { config ->
                composable(
                    route = config.route,
                    popExitTransition = config.popExitTransition,
                    popEnterTransition = config.popEnterTransition,
                    exitTransition = config.exitTransition,
                    enterTransition = config.enterTransition
                ) { navBackStackEntry ->
                    config.composableContent(navBackStackEntry, navController)
                }

            }
        }

    }
}


@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(parentEntry)
}
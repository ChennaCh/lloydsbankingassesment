package com.frost.leap.routing

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavController
import com.frost.leap.architecture.onboarding.presentation.screens.SignInPage
import com.frost.leap.architecture.onboarding.presentation.screens.SignUpPage
import com.frost.leap.architecture.onboarding.presentation.viewmodel.OnBoardingViewModel
import com.frost.leap.screens.SplashScreen
import com.frost.leap.screens.VerifyOTPScreen
import com.frost.leap.screens.VideoPlayerScreenCompose
import com.frost.leap.screens.onboarding.OnBoardingScreenCompose
import com.frost.leap.screens.otp.OTPScreen

class ComposableConfigImpl {

    companion object {

        @OptIn(ExperimentalAnimationApi::class)
        fun getSplashScreenComposable(navController: NavController): ComposableConfigModel {
            return ComposableConfigModel(
                route = Screen.SplashScreen.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(400)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(400)
                    )
                },
                popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(400)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(400)
                    )
                },
                composableContent = { _, _ ->
                    SplashScreen(navController = navController)
                }
            )
        }

        @OptIn(ExperimentalAnimationApi::class)
        fun getOnBoardingComposable(navController: NavController): List<ComposableConfigModel> {

            return listOf(

                // onboarding composable ...
                ComposableConfigModel(
                    route = Screen.OnBoardingPage.route,
                    enterTransition = {
                        slideIntoContainer(
                            AnimatedContentScope.SlideDirection.Left,
                            animationSpec = tween(400)
                        )
                    },
                    exitTransition = {
                        slideOutOfContainer(
                            AnimatedContentScope.SlideDirection.Left,
                            animationSpec = tween(400)
                        )
                    },
                    popEnterTransition = {
                        slideIntoContainer(
                            AnimatedContentScope.SlideDirection.Right,
                            animationSpec = tween(400)
                        )
                    },
                    popExitTransition = {
                        slideOutOfContainer(
                            AnimatedContentScope.SlideDirection.Right,
                            animationSpec = tween(400)
                        )
                    },

                    composableContent = { navBackStackEntry, _ ->
                        val viewModel =
                            navBackStackEntry.sharedViewModel<OnBoardingViewModel>(navController)
                        OnBoardingScreenCompose(
                            navController = navController,
                            stateFlow = viewModel.usernameState,
                            onEvent = viewModel::onEvent
                        )
                    }
                ),
                //Otp composables
                ComposableConfigModel(
                    route = Screen.OTPPage.route,
                    enterTransition = {
                        slideIntoContainer(
                            AnimatedContentScope.SlideDirection.Left,
                            animationSpec = tween(400)
                        )
                    },
                    exitTransition = {
                        slideOutOfContainer(
                            AnimatedContentScope.SlideDirection.Left,
                            animationSpec = tween(400)
                        )
                    },
                    popEnterTransition = {
                        slideIntoContainer(
                            AnimatedContentScope.SlideDirection.Right,
                            animationSpec = tween(400)
                        )
                    },
                    popExitTransition = {
                        slideOutOfContainer(
                            AnimatedContentScope.SlideDirection.Right,
                            animationSpec = tween(400)
                        )
                    },

                    composableContent = { _, _ ->
                        OTPScreen(navController = navController)
                    }
                ),
                // Sign up composable ...
                ComposableConfigModel(
                    route = Screen.SignUpPage.route,
                    enterTransition = {
                        slideIntoContainer(
                            AnimatedContentScope.SlideDirection.Left,
                            animationSpec = tween(400)
                        )
                    },
                    exitTransition = {
                        slideOutOfContainer(
                            AnimatedContentScope.SlideDirection.Left,
                            animationSpec = tween(400)
                        )
                    },
                    popEnterTransition = {
                        slideIntoContainer(
                            AnimatedContentScope.SlideDirection.Right,
                            animationSpec = tween(400)
                        )
                    },
                    popExitTransition = {
                        slideOutOfContainer(
                            AnimatedContentScope.SlideDirection.Right,
                            animationSpec = tween(400)
                        )
                    },
                    composableContent = { navBackStackEntry, _ ->
                        val viewModel =
                            navBackStackEntry.sharedViewModel<OnBoardingViewModel>(navController)
                        SignUpPage(navController = navController)
                    }
                ),
                // Sign in composable ...
                ComposableConfigModel(
                    route = Screen.SignInPage.route,
                    enterTransition = {
                        slideIntoContainer(
                            AnimatedContentScope.SlideDirection.Left,
                            animationSpec = tween(400)
                        )
                    },
                    exitTransition = {
                        slideOutOfContainer(
                            AnimatedContentScope.SlideDirection.Left,
                            animationSpec = tween(400)
                        )
                    },
                    popEnterTransition = {
                        slideIntoContainer(
                            AnimatedContentScope.SlideDirection.Right,
                            animationSpec = tween(400)
                        )
                    },
                    popExitTransition = {
                        slideOutOfContainer(
                            AnimatedContentScope.SlideDirection.Right,
                            animationSpec = tween(400)
                        )
                    },
                    composableContent = { navBackStackEntry, _ ->
                        val viewModel =
                            navBackStackEntry.sharedViewModel<OnBoardingViewModel>(navController)
                        SignInPage(
                            navController = navController,
                            stateFlow = viewModel.usernameState,
                            onEvent = viewModel::onEvent
                        )
                    }
                ),
                //VerifyOTP
                ComposableConfigModel(
                    route = Screen.VerifyOtpPage.route,
                    enterTransition = {
                        slideIntoContainer(
                            AnimatedContentScope.SlideDirection.Left,
                            animationSpec = tween(400)
                        )
                    },
                    exitTransition = {
                        slideOutOfContainer(
                            AnimatedContentScope.SlideDirection.Left,
                            animationSpec = tween(400)
                        )
                    },
                    popEnterTransition = {
                        slideIntoContainer(
                            AnimatedContentScope.SlideDirection.Right,
                            animationSpec = tween(400)
                        )
                    },
                    popExitTransition = {
                        slideOutOfContainer(
                            AnimatedContentScope.SlideDirection.Right,
                            animationSpec = tween(400)
                        )
                    },
                    composableContent = { navBackStackEntry, _ ->
                        val viewModel =
                            navBackStackEntry.sharedViewModel<OnBoardingViewModel>(navController)
                        VerifyOTPScreen(
                            navController = navController,
                            stateFlow = viewModel.otpVerifyState,
                            onEvent = viewModel::onEvent
                        )
                    }
                )

            )

        }

        @OptIn(ExperimentalAnimationApi::class)
        fun getMainFeatureComposable(navController: NavController): List<ComposableConfigModel> {

            return listOf(
                ComposableConfigModel(
                    route = Screen.VideoPlayerPage.route,
                    enterTransition = {
                        slideIntoContainer(
                            AnimatedContentScope.SlideDirection.Left,
                            animationSpec = tween(400)
                        )
                    },
                    exitTransition = {
                        slideOutOfContainer(
                            AnimatedContentScope.SlideDirection.Left,
                            animationSpec = tween(400)
                        )
                    },
                    popEnterTransition = {
                        slideIntoContainer(
                            AnimatedContentScope.SlideDirection.Right,
                            animationSpec = tween(400)
                        )
                    },
                    popExitTransition = {
                        slideOutOfContainer(
                            AnimatedContentScope.SlideDirection.Right,
                            animationSpec = tween(400)
                        )
                    },
                    composableContent = { _, _ ->
                        VideoPlayerScreenCompose(navController = navController)
                    }
                )

            )
        }

//        @OptIn(ExperimentalAnimationApi::class)
//        fun generateComposables(navController: NavController): List<ComposableConfigModel> {
//
//            // return the list which contains the composable information...
//            return listOf(
//                // OnBoardingPage composable...
//                ComposableConfigModel(
//                    route = Screen.OnBoardingPage.route,
//                    exitTransition = {
//                        slideOutHorizontally(
//                            targetOffsetX = { -300 },
//                            animationSpec = tween(
//                                durationMillis = 300,
//                                easing = FastOutSlowInEasing
//                            )
//                        ) +
//                                fadeOut(animationSpec = tween(300))
//                    },
//                    popEnterTransition = {
//                        slideInHorizontally(
//                            initialOffsetX = { 300 },
//                            animationSpec = tween(
//                                durationMillis = 300,
//                                easing = FastOutSlowInEasing
//                            )
//                        ) +
//                                fadeIn(animationSpec = tween(300))
//                    },
//                    composableContent = {
//                        OnBoardingScreenCompose(navController = navController)
//
//                    }
//
//                ),
//                // Sign up page composable ...
//                ComposableConfigModel(route = Screen.SignUpPage.route,
//                    enterTransition = {
//                        slideInHorizontally(
//                            initialOffsetX = { 300 },
//                            animationSpec = tween(
//                                durationMillis = 300,
//                                easing = FastOutSlowInEasing
//                            )
//                        ) + fadeIn(animationSpec = tween(300))
//                    },
//                    popExitTransition = {
//                        slideOutHorizontally(
//                            targetOffsetX = { -300 },
//                            animationSpec = tween(
//                                durationMillis = 300,
//                                easing = FastOutSlowInEasing
//                            )
//                        ) +
//                                fadeOut(animationSpec = tween(300))
//                    },
//                    composableContent = {
//                        SignUpPage(
//                            navController = navController,
//                            onBackPressed = { })
//                    }
//                ),
//
//                // Sign in page composable ...
//                ComposableConfigModel(route = Screen.SignInPage.route,
//                    enterTransition = {
//                        slideInHorizontally(
//                            initialOffsetX = { 300 },
//                            animationSpec = tween(
//                                durationMillis = 300,
//                                easing = FastOutSlowInEasing
//                            )
//                        ) + fadeIn(animationSpec = tween(300))
//                    },
//                    popExitTransition = {
//                        slideOutHorizontally(
//                            targetOffsetX = { 900 },
//                            animationSpec = tween(
//                                durationMillis = 300,
//                                easing = FastOutSlowInEasing
//                            )
//                        ) +
//                                fadeOut(animationSpec = tween(300))
//                    },
//                    composableContent = {
//                        val onBoardingViewModel = hiltViewModel<OnBoardingViewModel>()
//                        SignInPage(
//                            navController = navController,
//                            state = onBoardingViewModel.usernameState.value,
//                            onEvent = onBoardingViewModel::onEvent
//                        )
//                    }),
//
//                // Verify OTP page composable ...
//                ComposableConfigModel(route = Screen.VerifyOtpPage.route,
//                    enterTransition = {
//                        slideInHorizontally(
//                            initialOffsetX = { 300 },
//                            animationSpec = tween(
//                                durationMillis = 300,
//                                easing = FastOutSlowInEasing
//                            )
//                        ) + fadeIn(animationSpec = tween(300))
//                    },
//                    popExitTransition = {
//                        slideOutHorizontally(
//                            targetOffsetX = { 900 },
//                            animationSpec = tween(
//                                durationMillis = 300,
//                                easing = FastOutSlowInEasing
//                            )
//                        ) +
//                                fadeOut(animationSpec = tween(300))
//                    },
//                    composableContent = {
//                        val onBoardingViewModel = hiltViewModel<OnBoardingViewModel>()
//                        VerifyOTPScreen(
//                            navController = navController,
//                            state = onBoardingViewModel.otpVerifyState.value,
//                            onEvent = onBoardingViewModel::onEvent
//                        )
//                    }),
//
//                // Video Player composable ...
//                ComposableConfigModel(route = Screen.VideoPlayerPage.route,
//                    exitTransition = {
//                        slideOutHorizontally(
//                            targetOffsetX = { -300 },
//                            animationSpec = tween(
//                                durationMillis = 300,
//                                easing = FastOutSlowInEasing
//                            )
//                        ) + fadeOut(animationSpec = tween(300))
//                    },
//
//
//                    popEnterTransition = {
//                        slideInHorizontally(
//                            initialOffsetX = { 300 },
//                            animationSpec = tween(
//                                durationMillis = 300,
//                                easing = FastOutSlowInEasing
//                            )
//                        ) + fadeIn(animationSpec = tween(300))
//                    },
//                    composableContent = { VideoPlayerScreenCompose(navController = navController) }
//                )
//
//            )
//        }

    }


}
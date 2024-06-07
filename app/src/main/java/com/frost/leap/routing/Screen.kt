package com.frost.leap.routing

import com.frost.leap.utils.Constants

sealed class Screen(val route:String) {
    object SplashScreen : Screen(Constants.NavigationStrings.SPLASH_NAVIGATION)
    object OnBoardingPage : Screen(Constants.NavigationStrings.ONBOARDING_NAVIGATION)
    object OTPPage : Screen(Constants.NavigationStrings.OTP_NAVIGATION)
    object SignUpPage : Screen(Constants.NavigationStrings.SIGN_UP_NAVIGATION)
    object SignInPage : Screen(Constants.NavigationStrings.SIGN_IN_NAVIGATION)
    object VerifyOtpPage : Screen(Constants.NavigationStrings.VERIFY_OTP_NAVIGATION)
    object VideoPlayerPage : Screen(Constants.NavigationStrings.VIDEO_PLAYER_SCREEN)
}
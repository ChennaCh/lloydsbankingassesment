package com.frost.leap.architecture.onboarding.presentation

sealed class OnBoardingEvent {
    data class RequestLoginOrSignupAuth(val mobileNumber: String) : OnBoardingEvent()
    data class VerifyOTP(val mobileNumber: String, val otpNumber: String) : OnBoardingEvent()
    object RefreshState: OnBoardingEvent()
}
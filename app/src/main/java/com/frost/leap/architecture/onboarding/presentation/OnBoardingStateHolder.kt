package com.frost.leap.architecture.onboarding.presentation

import com.google.gson.JsonObject

data class OnBoardingStateHolder(
    val isLoading: Boolean = false,
    val data: JsonObject? = null,
    val error: String = "",
    val message: String = ""
)

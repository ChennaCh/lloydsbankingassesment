package com.frost.leap.architecture.onboarding.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.frost.leap.architecture.onboarding.domain.use_case.OnBoardingUseCase
import com.frost.leap.architecture.onboarding.domain.use_case.VerifyOTPUseCase
import com.frost.leap.architecture.onboarding.presentation.OnBoardingEvent
import com.frost.leap.architecture.onboarding.presentation.OnBoardingStateHolder
import com.frost.leap.components.refreshtoken.BaseUserCase
import com.frost.leap.components.refreshtoken.BaseViewModel
import com.frost.leap.components.refreshtoken.RefreshTokenState
import com.frost.leap.utils.BaseResponseModel
import com.frost.leap.utils.Constants
import com.frost.leap.utils.Utility
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val loginSignupUseCase: OnBoardingUseCase,
    private val verifyOTPUseCase: VerifyOTPUseCase,
    baseUserCase: BaseUserCase
) : BaseViewModel(baseUserCase = baseUserCase) {

    // Assume the username is stored in a state variable
    private val _usernameState = MutableStateFlow(OnBoardingStateHolder())
    val usernameState = _usernameState.asStateFlow()

    private val _otpVerifyState = MutableStateFlow(OnBoardingStateHolder())
    val otpVerifyState = _otpVerifyState.asStateFlow()

    fun onEvent(event: OnBoardingEvent) {
        when (event) {
            is OnBoardingEvent.RequestLoginOrSignupAuth -> {
                viewModelScope.launch {
                    requestLoginOrSignUpAuth(event.mobileNumber)
                }
            }

            is OnBoardingEvent.VerifyOTP -> {
                viewModelScope.launch {
                    verifyOTP(event.mobileNumber, event.otpNumber)
                }
            }

            is OnBoardingEvent.RefreshState -> {
                viewModelScope.launch {
                    _usernameState.update { it.copy(isLoading = false, error = "") }
                }
            }
        }
    }

    private suspend fun requestLoginOrSignUpAuth(mobileNumber: String) {
//        _usernameState.update { it.copy(isLoading = true) }
        val response = loginSignupUseCase.sendOTP(mobileNumber = "897747410",_usernameState)

        viewModelScope.launch {
            // Handle the ApiResponse based on the response code
            when (response) {
                is BaseResponseModel.Success<*> -> {
                    // Handle the successful response
                    val data = response.data
                    val code = response.code
                    _usernameState.update { it.copy(data = data as? JsonObject) }
                    // Process the data and code accordingly
                }

                is BaseResponseModel.StatusCode<*> -> {
                    // Handle the error response
                    val code = response.code
                    val message = Utility.showErrorMessage(response.response.errorBody())
                    // Process the error code and message accordingly
                    _usernameState.update { it.copy(error = message) }
                }

                else -> {
                    _usernameState.update { it.copy(error = Constants.SOMETHING_WENT_WRONG) }
                }
            }
        }
    }

    private suspend fun verifyOTP(mobileNumber: String, otpNumber: String) {
        _otpVerifyState.update { it.copy(isLoading = true) }

        val response =
            verifyOTPUseCase.verifyOTP(mobileNumber = mobileNumber, otpNumber = otpNumber)

        viewModelScope.launch {
            // Handle the ApiResponse based on the response code
            when (response) {
                is BaseResponseModel.Success<*> -> {
                    // Handle the successful response
                    val data = response.data
                    val code = response.code
                    _otpVerifyState.update { it.copy(data = data as? JsonObject) }
                    // Process the data and code accordingly
                }

                is BaseResponseModel.StatusCode<*> -> {
                    // Handle the error response
                    val message = Utility.showErrorMessage(response.response.errorBody())
                    // Process the error code and message accordingly
                    _otpVerifyState.update { it.copy(error = message) }
                    if (response.code == 401) {
                        refreshToken()
                        when (refreshTokenState.value) {
                            is RefreshTokenState.TokenRefreshed -> {
                                verifyOTP(mobileNumber = mobileNumber, otpNumber = otpNumber)
                            }

                            is RefreshTokenState.Error -> {

                            }

                            else -> {}
                        }
                    } else if (response.code == 500) {
                        _otpVerifyState.update { it.copy(error = Constants.SOMETHING_WENT_WRONG_SERVER) }
                    }
                }

                else -> {
                    _otpVerifyState.update { it.copy(error = Constants.SOMETHING_WENT_WRONG) }
                }
            }
        }
    }
}
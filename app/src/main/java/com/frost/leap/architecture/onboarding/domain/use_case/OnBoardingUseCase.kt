package com.frost.leap.architecture.onboarding.domain.use_case

import com.frost.leap.architecture.onboarding.domain.repository.UserRepositoryManager
import com.frost.leap.architecture.onboarding.presentation.OnBoardingStateHolder
import com.frost.leap.utils.BaseResponseModel
import com.frost.leap.utils.Utility
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class OnBoardingUseCase @Inject constructor(
    private val userRepositoryManager: UserRepositoryManager
) {

    suspend fun sendOTP(
        mobileNumber: String,
        _mobileState: MutableStateFlow<OnBoardingStateHolder>
    ): BaseResponseModel<JsonObject> {
        // Perform validation
        validateInput(mobileNumber, _mobileState)
        return userRepositoryManager.requestLoginOrSignUp(
            "91",
            mobileNumber = mobileNumber
        )
    }

    private fun validateInput(
        mobileNumber: String,
        _mobileState: MutableStateFlow<OnBoardingStateHolder>
    ): Boolean {
        if (mobileNumber.isBlank()) {
//            throw ValidationException("Phone number cannot be empty")
            _mobileState.update { it.copy(message = "Phone number cannot be empty") }
            return false;
        }

        if (!Utility.isValidMobile(mobileNumber)) {
//            throw ValidationException("The phone number you have entered is invalid")
            _mobileState.update { it.copy(message = "The phone number you have entered is invalid") }
            return false;
        }

        return true;
    }

}
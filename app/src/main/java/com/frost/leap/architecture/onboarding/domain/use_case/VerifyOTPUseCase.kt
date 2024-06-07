package com.frost.leap.architecture.onboarding.domain.use_case

import com.frost.leap.architecture.onboarding.domain.repository.UserRepositoryManager
import com.frost.leap.utils.BaseResponseModel
import com.google.gson.JsonObject
import javax.inject.Inject

class VerifyOTPUseCase @Inject constructor(
    private val userRepositoryManager: UserRepositoryManager
) {

    suspend fun verifyOTP(mobileNumber: String, otpNumber: String): BaseResponseModel<JsonObject> {
        return userRepositoryManager.verifyOTPForLoginOrSignUp(
            mobileNumber = mobileNumber,
            otpNumber = otpNumber,
        )
    }
}
package com.frost.leap.architecture.onboarding.domain.repository

import com.frost.leap.architecture.onboarding.data.model.UserModel
import com.frost.leap.storage.DataStoreManager
import com.frost.leap.utils.BaseResponseModel
import com.google.gson.JsonObject

interface UserRepositoryManager {

    suspend fun requestLoginOrSignUp(
        countryCode: String,
        mobileNumber: String
    ): BaseResponseModel<JsonObject>

    suspend fun verifyOTPForLoginOrSignUp(
        mobileNumber: String,
        otpNumber: String
    ): BaseResponseModel<JsonObject>

    suspend fun getUserDetails(): UserModel

    suspend fun isTokenRefreshed(): BaseResponseModel<JsonObject>

    suspend fun dataStoreManger(): DataStoreManager

}
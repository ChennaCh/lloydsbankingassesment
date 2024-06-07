package com.frost.leap.architecture.onboarding.data.repository

import com.frost.leap.architecture.onboarding.data.model.UserModel
import com.frost.leap.architecture.onboarding.data.network.IUserService
import com.frost.leap.architecture.onboarding.domain.repository.UserRepositoryManager
import com.frost.leap.model.UserAgent
import com.frost.leap.storage.DataStoreManager
import com.frost.leap.utils.BaseResponseModel
import com.frost.leap.utils.Constants
import com.frost.leap.utils.SafeApiRequest
import com.frost.leap.utils.Utility
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepoImplManager @Inject constructor(
    private val iUserService: IUserService,
    val dataStoreManager: DataStoreManager
) :
    UserRepositoryManager,
    SafeApiRequest() {
    override suspend fun requestLoginOrSignUp(
        countryCode: String,
        mobileNumber: String
    ): BaseResponseModel<JsonObject> {

        val jsonObject = JsonObject()
        jsonObject.addProperty("countryCode", countryCode)
        jsonObject.addProperty("mobile", mobileNumber)
        val signature: String = Utility.getAppSignature()

        val response =
            safeApiRequest {
                iUserService.requestLoginOrSignUp(
                    signature = signature,
                    jsonObject = jsonObject
                )
            }
        return response
    }

    override suspend fun verifyOTPForLoginOrSignUp(
        mobileNumber: String,
        otpNumber: String
    ): BaseResponseModel<JsonObject> {
        val jsonObject = JsonObject()
        jsonObject.addProperty("countryCode", "91")
        jsonObject.addProperty("mobile", mobileNumber)
        jsonObject.addProperty("otp", otpNumber)
        val signature: String = Utility.getAppSignature()
        val UserAgentType = object : TypeToken<UserAgent?>() {}.type

        val response =
            safeApiRequest {
                iUserService.verifyOTPForLoginOrSignUp(
                    signature = signature,
                    deviceType = "MOBILE",
                    useragent = Gson().toJson(UserAgent(), UserAgentType),
                    jsonObject = jsonObject
                )
            }
        return response
    }

    override suspend fun getUserDetails(): UserModel {
        TODO("Not yet implemented")
    }

    override suspend fun isTokenRefreshed(): BaseResponseModel<JsonObject> {

        val userAgent = object : TypeToken<UserAgent?>() {}.type
        var token = ""
        dataStoreManager.getStringValueByKey(Constants.TOKEN).map {
            token = it!!
        }

        val response =
            safeApiRequest {
                iUserService.refreshToken(
                    deviceType = "MOBILE",
                    useragent = Gson().toJson(UserAgent(), userAgent),
                    browserIp = Utility.getIpAddress(),
                    authorization = token
                )
            }

        return response
    }

    override suspend fun dataStoreManger(): DataStoreManager {
        return dataStoreManager
    }

}
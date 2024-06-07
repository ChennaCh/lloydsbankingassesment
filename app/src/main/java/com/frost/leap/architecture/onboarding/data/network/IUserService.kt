package com.frost.leap.architecture.onboarding.data.network

import com.frost.leap.architecture.onboarding.data.model.UserModel
import com.frost.leap.utils.Constants
import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface IUserService {

    @POST(Constants.UserConstants.REQUEST_LOGIN_OR_SIGN_UP_URL)
    suspend fun requestLoginOrSignUp(
        @Header("mobileToken") signature: String,
        @Body jsonObject: JsonObject
    ): Response<JsonObject>

    @POST(Constants.UserConstants.VERIFY_OTP_FOR_LOGIN_OR_SIGN_UP_URL)
    suspend fun verifyOTPForLoginOrSignUp(
        @Header("mobileToken") signature: String,
        @Header("X-Device-For") deviceType: String,
        @Header("User-Agent") useragent: String,
        @Body jsonObject: JsonObject
    ): Response<JsonObject>

    @GET(Constants.UserConstants.GET_USER_DETAILS_URL)
    fun getUserDetails(
        @Header("authorization") authorization: String?,
        @Header("userinfo") userInfo: JsonObject?,
        @Path("studentId") studentId: String?
    ): Observable<Response<UserModel?>?>?

    @POST(Constants.UserConstants.REFRESH_TOKEN_URL)
    suspend fun refreshToken(
        @Header("X-Device-For") deviceType: String,
        @Header("User-Agent") useragent: String,
        @Header("browserip") browserIp: String,
        @Header("authorization") authorization: String,
    ): Response<JsonObject>
}
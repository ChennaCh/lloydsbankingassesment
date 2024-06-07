package com.frost.leap.components.refreshtoken

import com.frost.leap.architecture.onboarding.domain.repository.UserRepositoryManager
import com.frost.leap.utils.BaseResponseModel
import com.google.gson.JsonObject
import javax.inject.Inject

class BaseUserCase @Inject constructor(
    val userRepositoryManager: UserRepositoryManager
) {

    suspend fun isRefreshToken(): BaseResponseModel<JsonObject> {
        return userRepositoryManager.isTokenRefreshed()
    }
}
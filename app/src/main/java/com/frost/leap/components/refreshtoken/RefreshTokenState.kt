package com.frost.leap.components.refreshtoken

sealed class RefreshTokenState {
    object Idle : RefreshTokenState()
    object Loading : RefreshTokenState()
    object TokenRefreshed : RefreshTokenState()
    object UnAuthorized : RefreshTokenState()
    data class Error(val error: String) : RefreshTokenState()
}
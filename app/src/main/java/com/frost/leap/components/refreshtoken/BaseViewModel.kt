package com.frost.leap.components.refreshtoken

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frost.leap.utils.BaseResponseModel
import com.frost.leap.utils.Constants
import com.frost.leap.utils.Utility
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(private val baseUserCase: BaseUserCase) :
    ViewModel() {

    private val _refreshTokenState = MutableStateFlow<RefreshTokenState>(RefreshTokenState.Idle)
    val refreshTokenState: StateFlow<RefreshTokenState> = _refreshTokenState

    private fun isNetworkAvailable(): Boolean {
        return Utility.isNetworkAvailable(Utility.getContext())
    }

    suspend fun refreshToken() {

        if (!isNetworkAvailable()) {
            _refreshTokenState.value =
                RefreshTokenState.Error(error = Constants.PLEASE_CHECK_INTERNET)
        }

        // Show loading state
        _refreshTokenState.value = RefreshTokenState.Loading
        val response = baseUserCase.isRefreshToken()
        var token = ""
        baseUserCase.userRepositoryManager.dataStoreManger()
            .getStringValueByKey(Constants.TOKEN).map {
                token = it!!
            }

        viewModelScope.launch {
            // Handle the ApiResponse based on the response code
            when (response) {
                is BaseResponseModel.Success<*> -> {
                    // Handle the successful response
                    if (response.code == 200) {
                        _refreshTokenState.value = RefreshTokenState.TokenRefreshed
                    }
                }

                is BaseResponseModel.StatusCode<*> -> {

                    val message = Utility.showErrorMessage(response.response.errorBody())
                    // Process the error code and message accordingly

                    if (response.code == 401) {
                        if (!token.equals(token)) {
                            _refreshTokenState.value = RefreshTokenState.TokenRefreshed
                        } else {
                            _refreshTokenState.value = RefreshTokenState.UnAuthorized
                        }
                    } else {
                        _refreshTokenState.value =
                            RefreshTokenState.Error(error = Constants.SOMETHING_WENT_WRONG)
                    }
                }

                else -> {
                    _refreshTokenState.value =
                        RefreshTokenState.Error(error = Constants.SOMETHING_WENT_WRONG)
                }
            }
        }


    }

}
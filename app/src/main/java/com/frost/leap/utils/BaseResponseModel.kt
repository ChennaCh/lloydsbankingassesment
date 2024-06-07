package com.frost.leap.utils

import retrofit2.Response

/**
 * Created by Chenna Rao on 06/06/2023.
 * <p>
 * Frost Interactive
 */
sealed class BaseResponseModel<T> {

//    var isError = false
//    var message: String? = null
//    var httpStatus: String? = null
//    var statusCode: Int? = null
//    var data: T? = null
//        private set
//
//    fun setData(data: T) {
//        this.data = data
//    }

    data class Success<T>(val data: T, val code: Int) : BaseResponseModel<T>()
    data class StatusCode<T>(val code: Int, val response: Response<T>) : BaseResponseModel<T>()
    class Loading<T>(data: T? = null) : BaseResponseModel<T>()

}

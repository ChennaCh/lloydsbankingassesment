package com.frost.leap.utils

import retrofit2.Response

/**
 * Created by Chenna Rao on 1/06/2023.
 * <p>
 * Frost Interactive
 */
abstract class SafeApiRequest {

    suspend fun <T : Any> safeApiRequest(call: suspend () -> Response<T>): BaseResponseModel<T> {
        val response = call.invoke()

        return if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                BaseResponseModel.Success(body, response.code())
            } else {
                BaseResponseModel.StatusCode(response.code(), response)
            }
        } else {
            BaseResponseModel.StatusCode(response.code(), response)
        }

//        if (response.isSuccessful) {
//            return response.body()!!
//        } else {
//            val responseCode = StringBuilder()
//            val responseErr = response.errorBody()?.string()
//            val message = StringBuilder()
//            responseCode.append(response.code())
//            responseErr.let {
//                try {
//                    message.append(it?.let { it1 -> JSONObject(it1).getString("error") })
//                } catch (e: JSONException) {
//                    e.printStackTrace()
//                    message.append(it?.let { it1 -> JSONObject(it1).getString("message") })
//                }
//            }
//            Logger.d("SAFE_API_REQUEST_EXCEPTION", "safeApiRequest: ${message.toString()}")
//            throw Exception(message.toString())
//        }
    }

}
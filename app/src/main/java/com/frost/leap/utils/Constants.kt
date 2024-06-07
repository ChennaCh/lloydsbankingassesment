package com.frost.leap.utils

import com.frost.leap.BuildConfig

/**
 * Created by Chenna Rao on 19/05/2023.
 * <p>
 * Frost Interactive
 */
class Constants {

    enum class Environment {
        PROD,
        DEV,
        TEST
    }

    companion object {

        const val TOKEN = "token"

        const val NO_DATA_AVAILABLE = "No data available"

        const val PLEASE_CHECK_INTERNET = "Please Check Your Internet Connection";

        const val SOMETHING_WENT_WRONG = "Something Went Wrong!"

        const val SOMETHING_WENT_WRONG_SERVER = "Something Went Wrong in Server"

        val environment: Environment =
            if (BuildConfig.ENVIRONMENT_TYPE == "TEST") Environment.TEST else if (BuildConfig.ENVIRONMENT_TYPE == "DEV") Environment.DEV else Environment.PROD

        const val APP_NAME = "ACE Online"

        const val BASE_URL = BuildConfig.BASE_URL

        const val CLOUD_FRONT_URL = BuildConfig.CLOUD_FRONT_URL

        const val AUTH_PREFIX = BuildConfig.AUTH_PREFIX

        const val STUDENT_PREFIX = BuildConfig.STUDENT_PREFIX

        const val STATS_PREFIX = BuildConfig.STATS_PREFIX

        const val USER_PREFIX = BuildConfig.USER_PREFIX

        const val CMS_PREFIX = BuildConfig.CMS_PREFIX

        const val SUBJECTS_PREFIX = BuildConfig.SUBJECTS_PREFIX

        const val PUBLISHER_PREFIX = BuildConfig.PUBLISHER_PREFIX

        const val PAYMENT_PREFIX = BuildConfig.PAYMENT_PREFIX

        const val NOTIFY_PREFIX = BuildConfig.NOTIFY_PREFIX

        const val BATCH_PREFIX = BuildConfig.BATCH_PREFIX

        const val LEAD_SQAURED_PREFIX = BuildConfig.LEAD_SQAURED_PREFIX

        const val FRESH_DESK_URL = BuildConfig.FRESH_DESK_BASE_URL

        const val KEYSTORE_PASSWORD: String = "#@nd-ileap"
    }

    object UserConstants {

        const val REQUEST_LOGIN_OR_SIGN_UP_URL: String = "$AUTH_PREFIX/student/send/otp"

        const val VERIFY_OTP_FOR_LOGIN_OR_SIGN_UP_URL: String = "$AUTH_PREFIX/student/verify/otp"

        const val GET_USER_DETAILS_URL: String = "$STUDENT_PREFIX/student/profile/{studentId}"

        const val REFRESH_TOKEN_URL: String = "$AUTH_PREFIX/refresh/token"


    }

    class NavigationStrings {
        companion object {

            const val SIGN_UP_NAVIGATION: String = "signup_fragment"

            const val SPLASH_NAVIGATION: String = "splash_screen"

            const val ONBOARDING_NAVIGATION: String = "onboarding_screen"

            const val OTP_NAVIGATION: String = "otp_screen"

            const val MAIN_SCREEN: String = "main_screen"

            const val VIDEO_PLAYER_SCREEN: String = "video_player_screen"

            const val VERIFY_OTP_NAVIGATION: String = "verify_otp_fragment"

            const val SIGN_IN_NAVIGATION: String = "signing_fragment"
        }
    }

}
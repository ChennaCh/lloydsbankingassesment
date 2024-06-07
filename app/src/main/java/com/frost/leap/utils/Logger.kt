package com.frost.leap.utils

import android.util.Log
import com.frost.leap.BuildConfig

/**
 * Created by Chenna Rao on 25/05/2023.
 * <p>
 * Frost Interactive
 */
object Logger {
    private var logStatus = BuildConfig.DEBUG
    const val TAG = Constants.APP_NAME
    fun error(message: String?, throwable: Throwable?) {
        if (logStatus) {
            Log.e(TAG, message, throwable)
        }
    }

    fun e(message: String) {
        if (logStatus) {
            Log.e(TAG, "" + message)
        }
    }

    fun e(tag: String?, message: String) {
        if (logStatus) {
            Log.e(tag, "" + message)
        }
    }

    fun d(message: String) {
        if (logStatus) {
            Log.d(TAG, "" + message)
        }
    }

    fun d(tag: String?, message: String) {
        if (logStatus) {
            Log.d(tag, "" + message)
        }
    }

    fun w(message: String) {
        if (logStatus) {
            Log.w(TAG, "" + message)
        }
    }

    fun w(tag: String?, message: String) {
        if (logStatus) {
            Log.w(tag, "" + message)
        }
    }

    fun i(message: String) {
        if (logStatus) {
            Log.i(TAG, "" + message)
        }
    }

    fun i(tag: String?, message: String) {
        if (logStatus) {
            Log.w(tag, "" + message)
        }
    }

    fun v(message: String) {
        if (logStatus) {
            Log.v(TAG, "" + message)
        }
    }

    fun v(tag: String?, message: String) {
        if (logStatus) {
            Log.v(tag, "" + message)
        }
    }

    fun e(app: String?, message: String?, e: Throwable?) {
        if (logStatus) {
            Log.e(app, message, e)
        }
    }
}

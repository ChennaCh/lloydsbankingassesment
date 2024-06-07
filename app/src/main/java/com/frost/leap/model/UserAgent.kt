package com.frost.leap.model

import android.os.Build
import com.frost.leap.BuildConfig
import com.frost.leap.utils.Utility

/**
 * Created by Gokul Kalagara (Mr. Pyscho) on 14-10-2019.
 *
 *
 * FROST
 */
class UserAgent {
    var ipAddress: String? = Utility.getIpAddress()
    var macAddress: String? = Utility.getPhoneUniqueId(Utility.getContext())
    var browser = "Native"
    var deviceOs = "Android " + Build.VERSION.RELEASE
    var deviceType = if (Utility.isTablet()) "Tablet" else "Smartphone"
    var appVersion = BuildConfig.VERSION_NAME
    var manufacturer = if (Build.MANUFACTURER == null) "UNKNOWN" else Build.MANUFACTURER
    var model = if (Build.MODEL == null) "UNKNOWN" else Build.MODEL
}

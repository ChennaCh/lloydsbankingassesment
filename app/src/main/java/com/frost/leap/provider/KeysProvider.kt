package com.frost.leap.provider

import android.annotation.SuppressLint
import android.content.pm.ApplicationInfo
import android.os.Build
import com.frost.leap.utils.Constants
import com.frost.leap.utils.Logger
import com.frost.leap.utils.Utility
import java.io.File
import java.io.IOException
import java.util.Arrays

/**
 * Created by Chenna Rao on 06/06/2023.
 * <p>
 * Frost Interactive
 */
@SuppressLint("UnsafeDynamicallyLoadedCode")
class KeysProvider private constructor() {
    val xCTSecretKey: String?
        external get
    val xCTMessage: String?
        external get
    val xCTPublicKey: String?
        external get
    val xCTDelimiter: String?
        external get
    val testRSAKey: String?
        external get
    val pallyConDRMLicenseUrl: String?
        external get
    val pallyConDRMOfflineLicenseUrl: String?
        external get
    val freshChatAppId: String?
        external get
    val freshChatAppKey: String?
        external get
    val appsFlyerDevKey: String?
        external get
    val freshChatDomain: String?
        external get
    val appRSAPublicKey: String?
        external get
    val appRSAPrivateKey: String?
        external get
    val zoomSdkApiKey: String?
        external get
    val zoomSecretKey: String?
        external get
    val zoomDomain: String?
        external get
    val qRCodeDevKey: String?
        external get

    companion object {
        private var provider: KeysProvider? = null
        val ENVIRONMENT_TYPE: Int = Constants.environment.ordinal

        init {
            try {
                // Try loading our native lib, see if it works...
                System.loadLibrary("deeplearncore")
            } catch (er: UnsatisfiedLinkError) {
                val appInfo: ApplicationInfo = Utility.getContext().applicationInfo
                val libName = "deeplearncore.so"
                var destPath: String = Utility.getContext().filesDir.toString()
                try {
                    val soName = destPath + File.separator + libName
                    File(soName).delete()
                    UnzipUtil.extractFile(
                        appInfo.sourceDir,
                        "lib/" + Arrays.toString(Build.SUPPORTED_ABIS) + "/" + libName,
                        destPath
                    )
                    System.load(soName)
                } catch (e: IOException) {
                    // extractFile to app files dir did not work. Not enough space? Try elsewhere...
                    destPath = Utility.getContext().externalCacheDir.toString()
                    // Note: location on external memory is not secure, everyone can read/write it...
                    // However we extract from a "secure" place (our apk) and instantly load it,
                    // on each start of the app, this should make it safer.
                    val soName = destPath + File.separator + libName
                    File(soName).delete() // this copy could be old, or altered by an attack
                    try {
                        UnzipUtil.extractFile(
                            appInfo.sourceDir,
                            "lib/" + Arrays.toString(Build.SUPPORTED_ABIS) + "/" + libName,
                            destPath
                        )
                        System.load(soName)
                    } catch (e2: IOException) {
                        Logger.d(Logger.TAG, "Exception in InstallInfo.init(): $e")
                        e.printStackTrace()
                    }
                }
            }
        }

        val instance: KeysProvider?
            get() {
                if (provider == null) provider = KeysProvider()
                return provider
            }
    }
}

package com.frost.leap.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.provider.Settings
import android.util.Base64
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import com.frost.leap.R
import com.frost.leap.model.BenefitModel
import com.google.gson.JsonParser
import okhttp3.ResponseBody
import java.net.InetAddress
import java.net.NetworkInterface
import java.nio.charset.StandardCharsets
import java.security.KeyFactory
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.security.spec.X509EncodedKeySpec
import java.util.Arrays
import java.util.Collections
import javax.crypto.Cipher
import javax.crypto.NoSuchPaddingException

@Suppress("DEPRECATION")
class Utility {

    companion object {

        private lateinit var appContext: Context

        fun init(context: Context) {
            appContext = context.applicationContext
        }

        fun isNetworkAvailable(context: Context): Boolean {
            return try {
                val connectivityManager =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val networkInfo = connectivityManager.activeNetworkInfo
                if (networkInfo == null || !networkInfo.isConnected) {
                    return false
                }

                true
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
                false
            }
        }

        fun getBenefitsList(): List<BenefitModel> {
            val benefitModelArrayList: MutableList<BenefitModel> = ArrayList<BenefitModel>()
            val benefitModel = BenefitModel(
                "Trusted for \n" +
                        "over 26 years",
                R.drawable.screen1,
                "Most reputable platform in the engineering community specialising in GATE and ESE segment."
            )
            val benefitModel1 = BenefitModel(
                "Highly qualified\n" +
                        "faculty",
                R.drawable.screen2,
                "Expert educators with over 10 years of experience in preparing students for competitive examinations."
            )
            val benefitModel2 = BenefitModel(
                "Delivering top ranks every year",
                R.drawable.screen3,
                "Consistently delivering excellent results through simulating real examination experience."
            )
            val benefitModel3 = BenefitModel(
                "",
                R.drawable.screen4,
                ""
            )

            benefitModelArrayList.add(benefitModel)
            benefitModelArrayList.add(benefitModel1)
            benefitModelArrayList.add(benefitModel2)
            benefitModelArrayList.add(benefitModel3)
            return benefitModelArrayList
        }

        fun getNavigationBarHeight(context: Context): Int {
            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getRealMetrics(displayMetrics)

            val usableHeight = displayMetrics.heightPixels
            val realMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(realMetrics)
            val realHeight = realMetrics.heightPixels

            return realHeight - usableHeight
        }

        fun getCipherData(data: String, publicKeyData: String?): String {
            var dataEncrypted: String? = null
            var cipherRSA: Cipher? = null
            try {
                val publicSpec = X509EncodedKeySpec(
                    Base64.decode(
                        publicKeyData,
                        Base64.NO_WRAP or Base64.NO_PADDING
                    )
                )
                val keyFactory = KeyFactory.getInstance("RSA")
                val publicKey = keyFactory.generatePublic(publicSpec)
                cipherRSA = Cipher.getInstance("RSA/ECB/PKCS1Padding")
                cipherRSA.init(Cipher.ENCRYPT_MODE, publicKey)
                dataEncrypted =
                    Base64.encodeToString(cipherRSA.doFinal(data.toByteArray()), Base64.NO_WRAP)
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            } catch (e: NoSuchPaddingException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            Logger.d("Cipher Data", dataEncrypted!!)
            return dataEncrypted
        }

        fun getContext(): Context {
            if (!::appContext.isInitialized) {
                throw IllegalStateException("AppContext has not been initialized.")
            }
            return appContext
        }

        fun isValidMobile(paramString: String?): Boolean {
            return paramString?.matches("[0-9]{10}".toRegex()) ?: false
        }

        fun getAppSignature(): String {

            val appSignaturesHashs = java.util.ArrayList<String>()

            try {
                // Get all package details
                val context = getContext()
                val packageName: String = context.packageName
                val packageManager: PackageManager = context.packageManager
                val signatures = packageManager.getPackageInfo(
                    packageName,
                    PackageManager.GET_SIGNATURES
                ).signatures
                for (signature in signatures) {
                    var hash: String? = null
                    hash = hash(packageName, signature.toCharsString())
                    if (hash != null) {
                        appSignaturesHashs.add(String.format("%s", hash))
                    }
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
                Logger.d("getAppSignatures", "Package not found")
            }
            return if (appSignaturesHashs.size > 0) appSignaturesHashs[0] else ""
        }

        fun hash(packageName: String, signature: String): String? {
            val HASH_TYPE = "SHA-256"
            val NUM_HASHED_BYTES = 9
            val NUM_BASE64_CHAR = 11
            val appInfo = "$packageName $signature"
            try {
                val messageDigest = MessageDigest.getInstance(HASH_TYPE)
                messageDigest.update(appInfo.toByteArray(StandardCharsets.UTF_8))
                var hashSignature = messageDigest.digest()
                // truncated into NUM_HASHED_BYTES
                hashSignature = Arrays.copyOfRange(hashSignature, 0, NUM_HASHED_BYTES)
                // encode into Base64
                var base64Hash =
                    Base64.encodeToString(hashSignature, Base64.NO_PADDING or Base64.NO_WRAP)
                base64Hash = base64Hash.substring(0, NUM_BASE64_CHAR)
                return base64Hash
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
                Logger.d("hash", "No Such Algorithm Exception")
            }
            return null
        }

        @Composable
        @SuppressLint("InternalInsetResource", "DiscouragedApi")
        fun getStatusBarHeight(context: Context): Dp {
            val density = LocalDensity.current

            val statusBarHeightPx = context.resources.getDimensionPixelSize(
                context.resources.getIdentifier(
                    "status_bar_height",
                    "dimen",
                    "android"
                )
            )

            return with(density) {
                statusBarHeightPx.toDp()
            }
        }

        fun getIpAddress(): String {
            try {
                val interfaces: List<NetworkInterface> =
                    Collections.list(NetworkInterface.getNetworkInterfaces())
                for (networkInterface in interfaces) {
                    val addresses: List<InetAddress> =
                        Collections.list(networkInterface.inetAddresses)
                    for (address in addresses) {
                        if (!address.isLoopbackAddress) {
                            return address.hostAddress!!
                        }
                    }
                }
            } catch (ex: java.lang.Exception) {
                ex.printStackTrace()
            } // for now eat exceptions
            return "192.168.0.1439"
        }

        @SuppressLint("HardwareIds")
        fun getPhoneUniqueId(context: Context): String? {
            return Settings.Secure.getString(
                context.contentResolver,
                Settings.Secure.ANDROID_ID
            )
        }

        fun isTablet(): Boolean {
            return try {
                val configuration = appContext.resources.configuration
                configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_LARGE

            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }

        fun showErrorMessage(responseBody: ResponseBody?): String {
            return try {
                if (responseBody == null) return Constants.SOMETHING_WENT_WRONG
                //            JsonObject jsonObject = (JsonObject) new JsonParser().parse(responseBody.string());
                val jsonObject = JsonParser.parseString(responseBody.string()).asJsonObject
                jsonObject["message"].asString
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
                Constants.SOMETHING_WENT_WRONG
            }
        }

    }
}
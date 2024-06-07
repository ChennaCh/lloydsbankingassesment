package com.frost.leap.provider

import android.util.Base64
import com.frost.leap.utils.Constants
import com.frost.leap.utils.Utility
import okhttp3.Request
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.util.UUID
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec


class XCTAuthorizationProvider private constructor() {

    private val provider: KeysProvider = KeysProvider.instance!!

    fun generateXCTAuthorization(request: Request): String {
        val timestamp = System.currentTimeMillis().toString()
        val nonce = UUID.randomUUID().toString()
        var hmacSignature: String? = null
        try {

            hmacSignature = provider.xCTSecretKey?.let {
                generateHmac256(
                    generateRequestStringToSign(request, timestamp, nonce),
                    it.toByteArray()
                )
            }
            hmacSignature = Base64.encodeToString(hmacSignature!!.toByteArray(), Base64.NO_WRAP)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return Utility.getCipherData(provider.xCTMessage!!, provider.xCTPublicKey!!) + ";" +
                nonce + ";" +
                timestamp + ";" +
                hmacSignature
    }

    private fun generateRequestStringToSign(
        request: Request,
        timestamp: String,
        nonce: String
    ): String {
        return request.method + provider.xCTDelimiter +
                timestamp + provider.xCTDelimiter +
                nonce + provider.xCTDelimiter +
                "/" + request.url.toString().replaceFirst(Constants.BASE_URL.toRegex(), "")
            .split("[?]".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray().get(0)
    }

    @Throws(InvalidKeyException::class, NoSuchAlgorithmException::class)
    private fun generateHmac256(message: String, key: ByteArray): String {
//        Logger.d("OkHttp", message);
        val bytes = hmac(key, message.toByteArray())
        return bytesToHex(bytes)
    }

    @Throws(NoSuchAlgorithmException::class, InvalidKeyException::class)
    private fun hmac(key: ByteArray, message: ByteArray): ByteArray {
        val mac = Mac.getInstance("HmacSHA256")
        mac.init(SecretKeySpec(key, "HmacSHA256"))
        return mac.doFinal(message)
    }

    private fun bytesToHex(bytes: ByteArray): String {
        val hexArray = "0123456789abcdef".toCharArray()
        val hexChars = CharArray(bytes.size * 2)
        var j = 0
        var v: Int
        while (j < bytes.size) {
            v = bytes[j].toInt() and 0xFF
            hexChars[j * 2] = hexArray[v ushr 4]
            hexChars[j * 2 + 1] = hexArray[v and 0x0F]
            j++
        }
        return String(hexChars)
    }

    companion object {
        var instance: XCTAuthorizationProvider? = null
            get() {
                if (field == null) field = XCTAuthorizationProvider()
                return field
            }
            private set
    }
}

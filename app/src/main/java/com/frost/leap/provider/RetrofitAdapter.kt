package com.frost.leap.provider

import com.frost.leap.BuildConfig
import com.frost.leap.R
import com.frost.leap.utils.Constants
import com.frost.leap.utils.RetrofitNetworkLogger
import com.frost.leap.utils.Utility
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.io.InputStream
import java.security.GeneralSecurityException
import java.security.KeyStore
import java.util.Arrays
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

/**
 * Created by Chenna Rao on 06/06/2023.
 * <p>
 * Frost Interactive
 */
class RetrofitAdapter {
    companion object {

        //For Retrofit
        private val logLevel =
            if (BuildConfig.DEBUG) RetrofitNetworkLogger.Level.BODY else RetrofitNetworkLogger.Level.NONE

        private fun newSslSocketFactory(): SSLSocketFactory {
            return try {
                // Get an instance of the Bouncy Castle KeyStore format
                val trusted = KeyStore.getInstance("BKS")
                // Get the raw resource, which contains the keystore with
                // your trusted certificates (root and any intermediate certs)
                val `in`: InputStream = Utility.getContext().getResources().openRawResource(
                    if (Constants.environment === Constants.Environment.TEST) R.raw.ileapssl_test else if (Constants.environment === Constants.Environment.DEV) R.raw.ileapssl_stagging else R.raw.ileapssl_prod
                )
                try {
                    // Initialize the keystore with the provided trusted certificates
                    // Provide the password of the keystore
                    trusted.load(`in`, Constants.KEYSTORE_PASSWORD.toCharArray())
                } finally {
                    `in`.close()
                }
                val tmfAlgorithm =
                    TrustManagerFactory.getDefaultAlgorithm()
                val tmf =
                    TrustManagerFactory.getInstance(tmfAlgorithm)
                tmf.init(trusted)
                val context = SSLContext.getInstance("TLS")
                context.init(null, tmf.trustManagers, null)
                context.socketFactory
            } catch (e: Exception) {
                e.printStackTrace()
                throw AssertionError(e)
            }
        }

        private fun getSystemDefaultTrustManager(): X509TrustManager {
            try {
                val trustManagerFactory = TrustManagerFactory.getInstance(
                    TrustManagerFactory.getDefaultAlgorithm()
                )
                trustManagerFactory.init(null as KeyStore?)
                val trustManagers = trustManagerFactory.trustManagers
                if (trustManagers.size != 1 || trustManagers.get(0) !is X509TrustManager) {
                    throw IllegalStateException(
                        "Unexpected default trust managers:"
                                + Arrays.toString(trustManagers)
                    )
                }
                return trustManagers[0] as X509TrustManager
            } catch (e: GeneralSecurityException) {
                throw java.lang.AssertionError() // The system has no TLS. Just give up.
            }
        }

        fun getClient(): OkHttpClient {
            val logging = RetrofitNetworkLogger()
            // set your desired log logLevel -> NONE
            logging.setLevel(logLevel)
            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(
                RetrofitAdapter.newSslSocketFactory(),
                RetrofitAdapter.getSystemDefaultTrustManager()
            )
            builder.addInterceptor(Interceptor { chain: Interceptor.Chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .header(
                        "X-CT-Authorization",
                        XCTAuthorizationProvider.instance!!.generateXCTAuthorization(original)
                    )
                    .method(original.method, original.body)
                    .build()
                chain.proceed(request)
            })
            builder.addInterceptor(logging)
                .connectTimeout(
                    5,
                    TimeUnit.MINUTES
                )
                .readTimeout(
                    5,
                    TimeUnit.MINUTES
                )
                .build()
            return builder.build()
        }


    }
}
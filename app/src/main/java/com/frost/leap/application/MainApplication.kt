package com.frost.leap.application

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.frost.leap.BuildConfig
import com.frost.leap.utils.Utility
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.perf.FirebasePerformance
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by Chenna Rao on 19/05/2023.
 * <p>
 * Frost Interactive
 */

@HiltAndroidApp
class MainApplication : MultiDexApplication() {

    private var mainApplication: MainApplication? = null


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }

    override fun onCreate() {
        super.onCreate()
        mainApplication = this

        Utility.init(this)

        // Firebase
        FirebaseApp.initializeApp(baseContext)
        FirebaseApp.getInstance()
        FirebasePerformance.getInstance()
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
        FirebaseAnalytics.getInstance(this).setAnalyticsCollectionEnabled(BuildConfig.DEBUG)

    }
}
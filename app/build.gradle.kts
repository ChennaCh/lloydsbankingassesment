plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")

//    id("com.google.firebase.firebase-perf")
}

@Suppress("UnstableApiUsage")
android {
    namespace = "com.frost.leap"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.frost.leap"
        multiDexEnabled = true
        minSdk = 23
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        externalNativeBuild {
            // For ndk-build, instead use the ndkBuild block.
            cmake {
                // Passes optional arguments to CMake.
                arguments("-DANDROID_ARM_NEON=TRUE", "-DANDROID_TOOLCHAIN=clang")

                // Sets a flag to enable format macro constants for the C compiler.
                cFlags("-D__STDC_FORMAT_MACROS")

                // Sets optional flags for the C++ compiler.
                cppFlags("-fexceptions", "-frtti")
            }
        }
    }

    // Specifies one flavor dimension.
    flavorDimensions += "version"
    productFlavors {

        create("dev") {
            applicationId = "com.frost.leap_test"
            dimension = "version"
            versionName = "0.0001"
            versionCode = 1

            buildConfigField("String", "ENVIRONMENT_TYPE", "${property("TEST_ENVIRONMENT_TYPE")}")
            buildConfigField("String", "BASE_URL", "${property("TEST_BASE_URL")}")
            buildConfigField("String", "CMS_PREFIX", "${property("TEST_CMS_PREFIX")}")
            buildConfigField("String", "SUBJECTS_PREFIX", "${property("TEST_SUBJECTS_PREFIX")}")
            buildConfigField("String", "PUBLISHER_PREFIX", "${property("TEST_PUBLISHER_PREFIX")}")
            buildConfigField("String", "STUDENT_PREFIX", "${property("TEST_STUDENT_PREFIX")}")
            buildConfigField("String", "STATS_PREFIX", "${property("TEST_STATS_PREFIX")}")
            buildConfigField("String", "USER_PREFIX", "${property("TEST_USER_PREFIX")}")
            buildConfigField("String", "PAYMENT_PREFIX", "${property("TEST_PAYMENT_PREFIX")}")
            buildConfigField("String", "AUTH_PREFIX", "${property("TEST_AUTH_PREFIX")}")
            buildConfigField("String", "BATCH_PREFIX", "${property("TEST_BATCH_PREFIX")}")
            buildConfigField("String", "NOTIFY_PREFIX", "${property("TEST_NOTIFY_PREFIX")}")
            buildConfigField(
                "String",
                "FRESH_DESK_BASE_URL",
                "${property("TEST_FRESH_DESK_BASE_URL")}"
            )
            buildConfigField("String", "ORG_CODE", "${property("TEST_ORG_CODE")}")
            buildConfigField("String", "MIXPANEL_TOKEN", "${property("TEST_MIXPANEL_TOKEN")}")
            buildConfigField(
                "String",
                "LEAD_SQUARED_BASE_URL",
                "${property("TEST_LEAD_SQUARED_BASE_URL")}"
            )
            buildConfigField("String", "GHOST_BASE_URL", "${property("TEST_GHOST_BASE_URL")}")
            buildConfigField("String", "CLOUD_FRONT_URL", "${property("TEST_CLOUD_FRONT_URL")}")
            buildConfigField(
                "String",
                "RESOURCES_CLOUD_FRONT_URL",
                "${property("TEST_RESOURCES_CLOUD_FRONT_URL")}"
            )
            buildConfigField(
                "String",
                "LEAD_SQAURED_PREFIX",
                "${property("TEST_LEAD_SQAURED_PREFIX")}"
            )

            resValue("string", "app_name", "\"${property("TEST_APP_NAME")}\"")
            resValue("string", "host_name", "\"${property("TEST_HOST_NAME")}\"")
            resValue("string", "host_url", "\"${property("TEST_HOST_URL")}\"")
            resValue("string", "RAZORPAY_API_KEY", "\"${property("TEST_RAZORPAY_API_KEY")}\"")
            resValue("string", "allow_perf", "\"${property("TEST_APP_PERFORMANCE_LOG")}\"")
//            resValue(
//                "string",
//                "freshchat_file_provider_authority",
//                "\"${property("applicationId")}\"" + ".fileprovider"
//            )

        }
        create("staging") {
            applicationId = "com.frost.leap_staging"
            dimension = "version"
            versionName = "0.0001"
            versionCode = 1

            buildConfigField("String", "ENVIRONMENT_TYPE", "${property("DEV_ENVIRONMENT_TYPE")}")
            buildConfigField("String", "BASE_URL", "${property("DEV_BASE_URL")}")
            buildConfigField("String", "CMS_PREFIX", "${property("DEV_CMS_PREFIX")}")
            buildConfigField("String", "SUBJECTS_PREFIX", "${property("DEV_SUBJECTS_PREFIX")}")
            buildConfigField("String", "PUBLISHER_PREFIX", "${property("DEV_PUBLISHER_PREFIX")}")
            buildConfigField("String", "STUDENT_PREFIX", "${property("DEV_STUDENT_PREFIX")}")
            buildConfigField("String", "STATS_PREFIX", "${property("DEV_STATS_PREFIX")}")
            buildConfigField("String", "USER_PREFIX", "${property("DEV_USER_PREFIX")}")
            buildConfigField("String", "PAYMENT_PREFIX", "${property("DEV_PAYMENT_PREFIX")}")
            buildConfigField("String", "AUTH_PREFIX", "${property("DEV_AUTH_PREFIX")}")
            buildConfigField("String", "BATCH_PREFIX", "${property("DEV_BATCH_PREFIX")}")
            buildConfigField("String", "NOTIFY_PREFIX", "${property("DEV_NOTIFY_PREFIX")}")
            buildConfigField(
                "String",
                "FRESH_DESK_BASE_URL",
                "${property("DEV_FRESH_DESK_BASE_URL")}"
            )
            buildConfigField("String", "ORG_CODE", "${property("DEV_ORG_CODE")}")
            buildConfigField("String", "MIXPANEL_TOKEN", "${property("DEV_MIXPANEL_TOKEN")}")
            buildConfigField(
                "String",
                "LEAD_SQUARED_BASE_URL",
                "${property("DEV_LEAD_SQUARED_BASE_URL")}"
            )
            buildConfigField("String", "GHOST_BASE_URL", "${property("DEV_GHOST_BASE_URL")}")
            buildConfigField("String", "CLOUD_FRONT_URL", "${property("DEV_CLOUD_FRONT_URL")}")
            buildConfigField(
                "String",
                "RESOURCES_CLOUD_FRONT_URL",
                "${property("DEV_RESOURCES_CLOUD_FRONT_URL")}"
            )
            buildConfigField(
                "String",
                "LEAD_SQAURED_PREFIX",
                "${property("DEV_LEAD_SQAURED_PREFIX")}"
            )

            resValue("string", "app_name", "\"${property("DEV_APP_NAME")}\"")
            resValue("string", "host_name", "\"${property("DEV_HOST_NAME")}\"")
            resValue("string", "host_url", "\"${property("DEV_HOST_URL")}\"")
            resValue("string", "RAZORPAY_API_KEY", "\"${property("DEV_RAZORPAY_API_KEY")}\"")
            resValue("string", "allow_perf", "\"${property("DEV_APP_PERFORMANCE_LOG")}\"")
//            resValue(
//                "string",
//                "freshchat_file_provider_authority",
//                "\"${property("applicationId")}\"" + ".fileprovider"
//            )
        }
        create("prod") {
            applicationId = "com.frost.leap"
            dimension = "version"
            versionName = "0.0001"
            versionCode = 1

            buildConfigField("String", "ENVIRONMENT_TYPE", "${property("PROD_ENVIRONMENT_TYPE")}")
            buildConfigField("String", "CMS_PREFIX", "${property("PROD_CMS_PREFIX")}")
            buildConfigField("String", "SUBJECTS_PREFIX", "${property("PROD_SUBJECTS_PREFIX")}")
            buildConfigField("String", "PUBLISHER_PREFIX", "${property("PROD_PUBLISHER_PREFIX")}")
            buildConfigField("String", "STUDENT_PREFIX", "${property("PROD_STUDENT_PREFIX")}")
            buildConfigField("String", "STATS_PREFIX", "${property("PROD_STATS_PREFIX")}")
            buildConfigField("String", "USER_PREFIX", "${property("PROD_USER_PREFIX")}")
            buildConfigField("String", "PAYMENT_PREFIX", "${property("PROD_PAYMENT_PREFIX")}")
            buildConfigField("String", "AUTH_PREFIX", "${property("PROD_AUTH_PREFIX")}")
            buildConfigField("String", "BATCH_PREFIX", "${property("PROD_BATCH_PREFIX")}")
            buildConfigField("String", "NOTIFY_PREFIX", "${property("PROD_NOTIFY_PREFIX")}")
            buildConfigField(
                "String",
                "FRESH_DESK_BASE_URL",
                "${property("PROD_FRESH_DESK_BASE_URL")}"
            )
            buildConfigField("String", "ORG_CODE", "${property("PROD_ORG_CODE")}")
            buildConfigField("String", "MIXPANEL_TOKEN", "${property("PROD_MIXPANEL_TOKEN")}")
            buildConfigField(
                "String",
                "LEAD_SQUARED_BASE_URL",
                "${property("PROD_LEAD_SQUARED_BASE_URL")}"
            )
            buildConfigField("String", "GHOST_BASE_URL", "${property("PROD_GHOST_BASE_URL")}")
            buildConfigField("String", "CLOUD_FRONT_URL", "${property("PROD_CLOUD_FRONT_URL")}")
            buildConfigField(
                "String",
                "RESOURCES_CLOUD_FRONT_URL",
                "${property("PROD_RESOURCES_CLOUD_FRONT_URL")}"
            )
            buildConfigField(
                "String",
                "LEAD_SQAURED_PREFIX",
                "${property("PROD_LEAD_SQAURED_PREFIX")}"
            )

            resValue("string", "app_name", "\"${property("PROD_APP_NAME")}\"")
            resValue("string", "host_name", "\"${property("PROD_HOST_NAME")}\"")
            resValue("string", "host_url", "\"${property("PROD_HOST_URL")}\"")
            resValue("string", "RAZORPAY_API_KEY", "\"${property("PROD_RAZORPAY_API_KEY")}\"")
            resValue("string", "allow_perf", "\"${property("PROD_APP_PERFORMANCE_LOG")}\"")
            //resValue(
//                "string",
//                "freshchat_file_provider_authority",
//                "\"${property("applicationId")}\"" + ".fileprovider"
//            )
        }

    }

    buildTypes {
        debug {

        }
        release {
            //Minify and Shrink should true when the app is ready to deploy
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        viewBinding = true
        dataBinding = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }

    dependenciesInfo {
        // Disables dependency metadata when building APKs.
        includeInApk = false
        // Disables dependency metadata when building Android App Bundles.
        includeInBundle = false
    }
    externalNativeBuild {
        cmake {
            path("CMakeLists.txt")
        }
    }

    ndkVersion = "22.1.7171670"

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

}


dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    //MultiDex
    implementation("androidx.multidex:multidex:2.0.1") {
        exclude(group = "com.android.support", module = "multidex")
    }

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    // Coroutine Lifecycle Scopes
//    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")

    //Activity KTX for viewModels()
    implementation("androidx.activity:activity-ktx:1.7.2")

    //Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.44")

    
    testImplementation("junit:junit:4.13.2")
//    testImplementation("org.junit.jupiter:junit-jupiter")
//    androidTestImplementation("org.junit.jupiter:junit-jupiter")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    // system ui controller
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.18.0")

    // paging 3
    implementation("androidx.paging:paging-runtime-ktx:3.1.1")
    implementation("androidx.paging:paging-compose:3.2.0-beta01")

    //API Calls
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.4.0")
//    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")

    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.6.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.6.0")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))

    //Image Loading
    implementation("io.coil-kt:coil-compose:2.2.2")

    //Navigation
    implementation("androidx.navigation:navigation-compose:2.6.0")

    //Firebase
    // Add the dependencies for the Firebase Cloud Messaging and Analytics libraries
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-core:21.1.1")
    implementation("com.google.firebase:firebase-messaging:23.1.2")
    implementation("com.google.firebase:firebase-crash:16.2.1")
    implementation("com.google.firebase:firebase-config:21.4.0")
    implementation("com.google.firebase:firebase-database:20.2.2")
    implementation("com.google.firebase:firebase-auth:22.0.0")
    implementation("com.google.firebase:firebase-analytics:21.3.0")
    implementation("com.google.firebase:firebase-crashlytics:18.3.7")
    implementation("com.google.firebase:firebase-perf:20.3.2")
    implementation("com.google.firebase:firebase-inappmessaging-display:20.3.2")
    implementation("com.google.firebase:firebase-messaging:23.1.2")
    implementation("com.google.firebase:firebase-iid:21.1.0") // add this
    implementation("com.google.firebase:firebase-appindexing:20.0.0")

    //exoplayer
    implementation("com.google.android.exoplayer:exoplayer:2.18.7")
    implementation("com.google.android.exoplayer:exoplayer-core:2.18.7")
    implementation("com.google.android.exoplayer:exoplayer-dash:2.18.7")
    implementation("com.google.android.exoplayer:exoplayer-hls:2.18.7")
    implementation("com.google.android.exoplayer:exoplayer-ui:2.18.7")
    implementation("com.google.android.exoplayer:extension-okhttp:2.18.7")

    //MixPanel
    implementation("com.mixpanel.android:mixpanel-android:7.0.0")
    implementation("com.android.installreferrer:installreferrer:2.2")

    // payments
    implementation("com.razorpay:checkout:1.6.25")

    //data-store
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    //Gson Converter
    implementation("com.google.code.gson:gson:2.9.0") // for JSON serialization

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // animation for navigation
    implementation("com.google.accompanist:accompanist-navigation-animation:0.30.1")


    // for testing the UI ...

    val compose_version = "1.4.3"
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:$compose_version")
    debugImplementation ("androidx.compose.ui:ui-tooling:$compose_version")
    debugImplementation ("androidx.compose.ui:ui-test-manifest:$compose_version")
    androidTestImplementation("androidx.test:runner:1.5.2")



}
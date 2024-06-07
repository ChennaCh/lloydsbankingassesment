// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.0.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        // Add the dependency for the Google services Gradle plugin
        classpath("com.google.gms:google-services:4.3.15")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.5")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.39")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")

        // Performance Monitoring plugin
//        classpath("com.google.firebase:perf-plugin:1.4.2") {
//            exclude(group = "com.google.guava", module = "guava-jdk5")
//        }

    }
}
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id ("org.jetbrains.kotlin.plugin.serialization") version "1.8.10"
}

android {
    namespace = "com.galaxy.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "BASE_URL",
            gradleLocalProperties(rootDir).getProperty("base_url")
        )
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation (project(":domain"))

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")

    //kotlinx.serialization
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    implementation ("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")

    //okhttp
    implementation ("com.squareup.okhttp3:okhttp-bom:4.11.0")
    implementation ("com.squareup.okhttp3:okhttp")
    implementation ("com.squareup.okhttp3:logging-interceptor")

    //Logger
    implementation ("com.orhanobut:logger:2.2.0")

    //sandwich
    implementation ("com.github.skydoves:sandwich:1.3.9")



    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
}
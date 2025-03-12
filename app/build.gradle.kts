plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "com.roomapi.apimvvm"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.roomapi.apimvvm"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    implementation("androidx.compose.runtime:runtime-livedata:1.4.0") // Make sure this is included


    /*test case*/
    testImplementation(libs.junit)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    implementation(libs.okhttp3.mockwebserver)
    implementation(libs.androidx.core.testing)

    /*dagger-hilt*/
    implementation(libs.dagger.hilt)
    kapt(libs.dagger.hilt.compiler)

    // Room Database dependencies using version catalog
    implementation(libs.room.runtime)
    kapt(libs.room.compiler)  // Room compiler for annotation processing
    implementation(libs.room.ktx)  // Optional: Kotlin extensions for Room

    // Retrofit and OkHttp dependencies using version catalog
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)  // Gson converter for Retrofit

    // Kotlin Coroutines dependencies using version catalog
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)  // For Android-specific coroutine support
    testImplementation(libs.coroutines.test)  // For testing coroutines

}
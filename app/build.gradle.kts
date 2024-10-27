plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.msa.hilt_compose_example"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.msa.hilt_compose_example"
        minSdk = 26
        targetSdk = 34
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
    // Allow references to generated code
    kapt {
        correctErrorTypes = true
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //hilt
    implementation(dependency.hilt.android)
    kapt(dependency.hilt.android.compiler)
    implementation(dependency.androidx.hilt.navigation.compose)

    //retrofit
    implementation (dependency.retrofit)
    implementation (dependency.converter.gson)
    implementation(dependency.okhttp)
    implementation (dependency.logging.interceptor)

    // Retrofit & Moshi
    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation ("com.squareup.moshi:moshi:1.12.0")
    implementation ("com.squareup.moshi:moshi-kotlin:1.12.0")

    // OkHttp & Chucker
    debugImplementation ("com.github.chuckerteam.chucker:library:3.5.2")
    releaseImplementation ("com.github.chuckerteam.chucker:library-no-op:3.5.2")

    implementation ("androidx.security:security-crypto:1.1.0-alpha03")

    // ViewModel utilities for Compose
    implementation(dependency.androidx.lifecycle.viewmodel.ktx)
    implementation(dependency.androidx.lifecycle.viewmodel.compose)

    // Lifecycle utilities for Compose
    implementation(dependency.androidx.lifecycle.runtime.compose)

    //timber
    implementation(dependency.timber)


    //Room Db
    implementation(dependency.androidx.room.runtime)
    // annotationProcessor(dependency.androidx.room.compiler)
    kapt(dependency.androidx.room.compiler)
    implementation(dependency.androidx.room.ktx)

    //material.icons
    implementation(dependency.androidx.material.icons.extended.android)

    //androidx.navigation
    implementation(dependency.androidx.navigation.compose)

    //coil loading image
    implementation(dependency.coil.compose)

    //navigation
    implementation(dependency.androidx.navigation.compose)
    implementation(dependency.androidx.material)
    implementation (dependency.state)

}
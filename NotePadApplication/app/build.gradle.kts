
plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id("kotlin-kapt")

}

android {
    namespace = "com.example.notepadapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.notepadapplication"
        minSdk = 21
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation (libs.androidx.core.ktx.v1120) // Latest version as of Jan 2025
    implementation (libs.androidx.appcompat.v170)
    implementation (libs.androidx.constraintlayout.v216)

    // Material Design
    implementation ("com.google.android.material:material:1.10.0")
    implementation ("androidx.recyclerview:recyclerview:1.4.0")


    //room database


    implementation("androidx.room:room-runtime:2.5.2")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.6") // Latest version as of January 2025

    implementation (libs.androidx.constraintlayout)
    implementation  ("androidx.appcompat:appcompat:1.7.0")
    annotationProcessor("androidx.room:room-compiler:2.4.2")
    kapt("androidx.room:room-compiler:2.4.2")
    implementation ("androidx.navigation:navigation-fragment-ktx:2.4.2")
    implementation ("androidx.navigation:navigation-ui-ktx:2.4.2")
    implementation("androidx.room:room-ktx:2.4.2")

    //KTX
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
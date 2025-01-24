import com.android.build.api.dsl.BuildFeatures

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.myweatherapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.myweatherapp"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true


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
    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.0")
    implementation ("com.squareup.retrofit2:adapter-rxjava3:2.9.0") // Optional for RxJava3 support
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    //location
    implementation("com.google.android.gms:play-services-location:21.0.1")

    //swipe Refresh layout
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")



    //Scable Size Unit
    implementation("com.intuit.sdp:sdp-android:1.1.0")
    implementation("com.intuit.ssp:ssp-android:1.1.0")


    // Coroutine support
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0") // For JSON parsing

    //navigation component
    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.5")
    //koin
    implementation ("io.insert-koin:koin-android:3.4.2")


    //implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    //retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    //view model
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    //koil
    implementation ("io.coil-kt:coil:2.5.0")
    implementation("com.google.android.gms:play-services-location:21.3.0")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}
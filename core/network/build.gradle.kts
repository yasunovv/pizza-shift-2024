plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlinx.serialization)
    id("kotlin-kapt")
    id ("dagger.hilt.android.plugin")


}

android {
    namespace = "com.yasunov.network2"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    implementation(libs.material)
    // Network
    implementation(libs.retrofit)
    implementation(libs.retrofit.adapters.result)
//    KotlinX
    implementation (libs.converter.kotlinx.serialization)
    implementation(libs.kotlinx.serialiaztion.json)
    //    Coroutines
    implementation(libs.kotlinx.coroutines.core)
//    Annotations
    implementation(libs.androidx.annotation)
    implementation(libs.coil.kt)
    implementation (libs.hilt.android)
    kapt (libs.hilt.android.compiler)

}
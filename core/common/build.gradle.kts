import org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
}

kotlin {
    explicitApi = ExplicitApiMode.Strict
}

android {
    namespace = "com.yasunov.common"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
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
    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.coroutines.core)
    api(libs.kotlinx.immutable)
//    Hilt
    implementation (libs.hilt.android)
    kapt (libs.hilt.android.compiler)
}

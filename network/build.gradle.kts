plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
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

}
plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-parcelize")
    kotlin("kapt")
}

version = "2.2.4"

android {
    compileSdk = 32
    buildToolsVersion = "30.0.3"

    defaultConfig {
        minSdk= 17
        targetSdk= 32
    }
    buildTypes {
        release {
            isMinifyEnabled= false
            setProguardFiles (listOf(getDefaultProguardFile ("proguard-android.txt"), "proguard-rules.pro"))
        }
    }
}

dependencies {
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.7.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1")

    implementation ("androidx.appcompat:appcompat:1.4.2")
    implementation ("com.google.android.material:material:1.6.1")
    implementation ("androidx.recyclerview:recyclerview:1.2.1")
    api ("com.github.bumptech.glide:glide:4.12.0")
    kapt ("com.github.bumptech.glide:compiler:4.11.0")

    testImplementation ("junit:junit:4.13.2")
}

repositories {
    mavenCentral()
}
plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-parcelize")
}

android {
    compileSdk = 32
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "ir.inbo.examples.filepicker"
        minSdk = 17
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            setProguardFiles(
                listOf(
                    getDefaultProguardFile("proguard-android.txt"),
                    "proguard-rules.pro"
                )
            )
        }
    }
}

dependencies {
    testImplementation("junit:junit:4.13.2")
    implementation(project(":filepicker"))
    implementation("androidx.appcompat:appcompat:1.4.2")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("com.github.xinyuez:easypermissions:2.0.1")
    implementation("com.google.android.material:material:1.6.1")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.7.0")
    // Required for instrumented tests
    androidTestImplementation("androidx.annotation:annotation:1.4.0")
    androidTestImplementation("androidx.test:runner:1.4.0")
    androidTestImplementation("androidx.test:rules:1.4.0")
    // Optional -- UI testing with Espresso
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.test.espresso:espresso-contrib:3.4.0")
    androidTestImplementation("androidx.test.espresso:espresso-intents:3.4.0")

//    compile 'com.squareup.leakcanary:leakcanary-android:1.5.1'
}

plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-parcelize")
    kotlin("kapt")
    id("com.jfrog.artifactory")
    id("maven-publish")
}

val libraryGroupId = "ir.inbo.libs"
val libraryArtifactId = "ComposeFilePicker"
val libraryVersion = "2.5.0"


val sourcesJar = tasks.register("sourcesJar", Jar::class) {
    from(android.sourceSets["main"].java.getSourceFiles())
    archiveClassifier.set("sources")
}


val javadoc = tasks.register("javadoc", Javadoc::class) {
    source = android.sourceSets["main"].java.getSourceFiles()
    classpath += project.files(android.bootClasspath)
}


val javadocJar = tasks.register("javadocJar", Jar::class) {
    dependsOn(javadoc)
    from(javadoc)
    archiveClassifier.set("javadoc")
}


publishing {
    publications {
        afterEvaluate {

            create<MavenPublication>("release") {
                from(components["release"])
                artifact(sourcesJar)
                artifact(javadocJar)

                groupId = libraryGroupId
                artifactId = libraryArtifactId
                version = libraryVersion

                // And here are some more properties that go into the pom file.
                pom {
                    packaging = "aar"
                    name.set("Inbo Compose File Picker")
                    description.set("This module is used for select file")
                    url.set("https://github.com/inboofficial/ComposeFilePicker")
                    licenses {
                        license {
                            name.set("Mobin Yardim")
                            url.set("github.com/inboOfficial")
                        }
                    }
                    developers {
                        developer {
                            name.set("Mobin Yardim")
                            email.set("mobinyardim@gmail.com")
                        }
                    }
                    scm {
                        url.set(pom.url.get())
                        connection.set("scm:git:${url.get()}.git")
                        developerConnection.set("scm:git:${url.get()}.git")
                    }
                }
            }
        }
    }

    repositories {
        maven {
            url = uri("https://inbo.jfrog.io/artifactory/inbo-public-repo/")
            credentials {
                username = properties["artifactory_username"] as String
                password = properties["artifactory_password"] as String
            }
        }
    }
}


artifactory {
    setContextUrl("https://inbo.jfrog.io/artifactory")
    publish {
        repository {
            setRepoKey("libs-release-local")
            setUsername(properties["artifactory_username"] as String)
            setPassword(properties["artifactory_password"] as String)
        }
        defaults {
            publications("aar")
            setPublishArtifacts(true)
            properties.put("qa.level", "basic")
            properties.put("q.os", "android")
            properties.put("dev.team", "core")
            setPublishPom(true)
        }
    }
}

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
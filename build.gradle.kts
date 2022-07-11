// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven {
            url = uri("https://inbo.jfrog.io/artifactory/inbo-public-repo/")
            credentials {
                username = properties["artifactory_username"] as String
                password = properties["artifactory_password"] as String
            }
        }
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.2.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.0")
        classpath("org.jfrog.buildinfo:build-info-extractor-gradle:4.28.4")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven(url = "https://maven.google.com")
        maven {
            url = uri("https://inbo.jfrog.io/artifactory/inbo-public-repo/")
            credentials {
                username = properties["artifactory_username"] as String
                password = properties["artifactory_password"] as String
            }
        }
    }
}

tasks.register<Delete>("clean").configure {
    delete(rootProject.buildDir)
}
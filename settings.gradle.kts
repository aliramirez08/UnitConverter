pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        // Android Gradle Plugin
        id("com.android.application") version "8.13.0"

        // Kotlin plugins
        id("org.jetbrains.kotlin.android") version "2.2.21"
        id("org.jetbrains.kotlin.kapt") version "2.2.21"

        // Compose compiler plugin (required for Kotlin 2.x + Compose)
        id("org.jetbrains.kotlin.plugin.compose") version "2.2.21"

        // Hilt
        id("com.google.dagger.hilt.android") version "2.57.2"
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject

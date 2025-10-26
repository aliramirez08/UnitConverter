plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("com.google.dagger.hilt.android")
    // Compose compiler plugin – DO NOT specify a version here (it’s provided by the Kotlin Gradle plugin)
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.example.unitconverter"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.unitconverter"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.google.dagger.hilt.android.testing.HiltTestRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        debug {
            // if these show deprecation, you can remove them; they are fine for now
            enableUnitTestCoverage = true
            enableAndroidTestCoverage = true
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures { compose = true }

    // Kotlin 2.x requires the Compose Compiler plugin (we already applied it in plugins {})
    composeCompiler {
        enableStrongSkippingMode = true
    }

    // JVM toolchain alignment (fixes mismatched target errors)
    kotlin {
        jvmToolchain(17)
    }

    testOptions {
        unitTests.isIncludeAndroidResources = false // keep false unless you use Robolectric
        animationsDisabled = true
    }

    packaging {
        resources.excludes += setOf("META-INF/AL2.0", "META-INF/LGPL2.1")
    }
}

dependencies {
    // Compose BOM
    val composeBom = platform("androidx.compose:compose-bom:2024.09.01")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    // Compose UI
    implementation("androidx.activity:activity-compose:1.11.0")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")

    // Lifecycle / ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.4")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.9.4")

    // Core
    implementation("androidx.core:core-ktx:1.17.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.4")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.57.2")
    kapt("com.google.dagger:hilt-android-compiler:2.57.2")
    implementation("androidx.hilt:hilt-navigation-compose:1.3.0")

    // Hilt - Instrumented tests
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.57.2")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.57.2")

    // Hilt - Local unit tests
    testImplementation("com.google.dagger:hilt-android-testing:2.57.2")
    kaptTest("com.google.dagger:hilt-android-compiler:2.57.2")

    // Unit test: JUnit + MockK (use a version compatible with Kotlin 2.x)
    testImplementation("junit:junit:4.13.2")
    testImplementation("io.mockk:mockk:1.14.6")

    // AndroidTest (Compose/JUnit/Espresso)
    androidTestImplementation("androidx.test.ext:junit:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}

kapt {
    correctErrorTypes = true
}

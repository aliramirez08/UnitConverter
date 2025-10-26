import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.testing.jacoco.tasks.JacocoReport

plugins.apply("jacoco")

extensions.configure<JacocoPluginExtension> {
    toolVersion = "0.8.11"
}

// ---------- UNIT TEST COVERAGE ----------
tasks.register<JacocoReport>("jacocoTestReport") {
    dependsOn(":app:testDebugUnitTest")

    reports {
        xml.required.set(true)
        html.required.set(true)
        csv.required.set(false)
    }

    val fileFilter = listOf(
        "**/R.class", "**/R$*.class", "**/BuildConfig.*", "**/Manifest*.*",
        "**/*Test*.*", "**/*Hilt*.*", "**/*Module*.*", "**/*_Factory*.*"
    )

    val kotlinDebugTree = fileTree("${project.buildDir}/tmp/kotlin-classes/debug") {
        exclude(fileFilter)
    }

    classDirectories.setFrom(files(kotlinDebugTree))
    sourceDirectories.setFrom(files("app/src/main/java"))
    executionData.setFrom(fileTree("${project.buildDir}/jacoco") {
        include("testDebugUnitTest.exec")
    })
}

// jacoco.gradle.kts (project root ONLY for coverage tasks)
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.testing.jacoco.tasks.JacocoReport

// Apply Jacoco in this script (no plugins {} block here)
apply(plugin = "jacoco")

extensions.configure<JacocoPluginExtension> {
    toolVersion = "0.8.11"
}

// ---------- Unit test coverage HTML ----------
tasks.register<JacocoReport>("jacocoTestReport") {
    dependsOn(":app:testDebugUnitTest")

    reports {
        xml.required.set(true)
        html.required.set(true)
        csv.required.set(false)
    }

    val fileFilter = listOf(
        "**/R.class","**/R$*.class","**/BuildConfig.*","**/Manifest*.*",
        "**/*Test*.*","**/*Hilt*.*","**/*Module*.*","**/*_Factory*.*"
    )

    val kotlinDebugTree = fileTree("${project.projectDir}/app/build/tmp/kotlin-classes/debug") {
        exclude(fileFilter)
    }

    classDirectories.setFrom(files(kotlinDebugTree))
    sourceDirectories.setFrom(files("app/src/main/java"))
    executionData.setFrom(fileTree("${project.projectDir}/app/build") {
        include("**/jacoco/testDebugUnitTest.exec")
    })
}

// ---------- Instrumented (androidTest) coverage HTML ----------
tasks.register<JacocoReport>("jacocoAndroidTestReport") {
    dependsOn(":app:connectedDebugAndroidTest")

    reports {
        xml.required.set(true)
        html.required.set(true)
        csv.required.set(false)
    }

    val fileFilter = listOf("**/R.class","**/R$*.class","**/BuildConfig.*","**/Manifest*.*")

    val kotlinDebugTree = fileTree("${project.projectDir}/app/build/tmp/kotlin-classes/debug") {
        exclude(fileFilter)
    }

    classDirectories.setFrom(files(kotlinDebugTree))
    sourceDirectories.setFrom(files("app/src/main/java"))
    executionData.setFrom(fileTree("${project.projectDir}/app/build") {
        include("outputs/code_coverage/**/connected/**.ec")
        include("outputs/code_coverage/**/connected/*coverage.ec")
    })
}

plugins {
    // Necessary to avoid the plugins being loaded multiple times in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidMultiplatformLibrary) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kotlinSerialization) apply false
    alias(libs.plugins.googleServices) apply false
}

if (System.getenv("CI") != "true") {
    allprojects {
        layout.buildDirectory.set(
            file("${System.getProperty("user.home")}/.cache/projects/test-54715c7c/build/${project.name}")
        )
    }
}


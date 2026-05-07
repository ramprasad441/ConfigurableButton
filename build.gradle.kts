plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.ksp) apply false // <--- Add this
}

tasks.register<Delete>("clean") {
    group = "build"
    description = "Deletes the root project build directory."

    delete(
        rootProject.layout.buildDirectory
            .get()
            .asFile,
    )
}

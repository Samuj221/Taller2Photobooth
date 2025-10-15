plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose") // <— NUEVO
}

android {
    namespace = "com.example.taller2photobooth"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.taller2photobooth"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures { compose = true }


}

dependencies {
    // BOM de Compose (usa el que ya traiga tu template; este funciona bien)
    implementation(platform("androidx.compose:compose-bom:2024.06.00"))
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3:1.2.1")
    debugImplementation("androidx.compose.ui:ui-tooling")

    // Accompanist Permissions
    implementation("com.google.accompanist:accompanist-permissions:0.34.0")

    // CameraX
    implementation("androidx.camera:camera-core:1.3.4")
    implementation("androidx.camera:camera-camera2:1.3.4")
    implementation("androidx.camera:camera-lifecycle:1.3.4")
    implementation("androidx.camera:camera-view:1.3.4")

    // Coil para mostrar imágenes
    implementation("io.coil-kt:coil-compose:2.6.0")

    // Icons extra
    implementation("androidx.compose.material:material-icons-extended:1.6.8")
}

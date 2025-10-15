plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
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

    // <<< AQUI unificamos Java a 1.8 >>>
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    packaging { resources.excludes += "/META-INF/{AL2.0,LGPL2.1}" }
}

// <<< AQUI unificamos Kotlin a 1.8 y usamos JDK 17 >>>
kotlin {
    jvmToolchain(17)
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_1_8)
    }
}

dependencies {
    // Compose base
    implementation(platform("androidx.compose:compose-bom:2024.06.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    debugImplementation("androidx.compose.ui:ui-tooling")

    // 👇 NECESARIAS para tus imports
    implementation("androidx.activity:activity-compose:1.9.0")              // setContent()
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")  // viewModel()

    // Resto de tu proyecto
    implementation("com.google.accompanist:accompanist-permissions:0.34.0")
    implementation("androidx.camera:camera-core:1.3.4")
    implementation("androidx.camera:camera-camera2:1.3.4")
    implementation("androidx.camera:camera-lifecycle:1.3.4")
    implementation("androidx.camera:camera-view:1.3.4")
    implementation("io.coil-kt:coil-compose:2.6.0")
    implementation("com.google.android.material:material:1.12.0")

    implementation("androidx.compose.material:material-icons-extended")

}


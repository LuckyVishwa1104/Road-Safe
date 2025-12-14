plugins {
    id("com.android.application")
    id("kotlin-android")
    id("dev.flutter.flutter-gradle-plugin")
}

import java.util.Properties

// Read local.properties for Flutter versioning
val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localPropertiesFile.inputStream().use { localProperties.load(it) }
}

val flutterVersionCode = (localProperties.getProperty("flutter.versionCode") ?: "1").toInt()
val flutterVersionName = (localProperties.getProperty("flutter.versionName") ?: "1.0")

android {
    namespace = "com.example.road_safe_app"

    compileSdk = (findProperty("flutter.compileSdkVersion")?.toString()?.toIntOrNull() ?: 34)
    findProperty("flutter.ndkVersion")?.toString()?.let { ndkVersion = it }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    // FIXED: Correct Kotlin DSL for adding kotlin src dir
    sourceSets["main"].java.srcDir("src/main/kotlin")

    defaultConfig {
        applicationId = "com.example.road_safe_app"

        minSdk = (findProperty("flutter.minSdkVersion")?.toString()?.toIntOrNull() ?: 26)
        targetSdk = (findProperty("flutter.targetSdkVersion")?.toString()?.toIntOrNull() ?: 34)

        versionCode = flutterVersionCode
        versionName = flutterVersionName
    }

    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    // FIXED: new DSL for AGP 8+
    androidResources {
        noCompress += listOf("tflite", "lite")
    }
}

flutter {
    source = "../.."
}

dependencies {
   // (Empty — but you can add deps here)
}

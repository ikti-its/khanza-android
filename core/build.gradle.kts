plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kotlinKsp)
    alias(libs.plugins.hiltAndroid)
}

android.sourceSets.all {
    java.srcDir("src/$name/kotlin")
}

android {
    namespace = "dev.ikti.core"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("boolean", "DEBUG", "true")
        buildConfigField("String", "BASE_URL", "\"https://simkes.fathoor.dev/v1/\"")
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    tasks.withType().configureEach {
        kotlinOptions {
            freeCompilerArgs = freeCompilerArgs + listOf(
                "-opt-in=kotlin.RequiresOptIn",
                "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
            )
        }
    }
}

dependencies {
//  Core
    api(libs.androidx.core.ktx)
    api(libs.androidx.lifecycle.runtime.ktx)

//  Compose
    api(libs.androidx.activity.compose)
    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.compose.ui.graphics)
    debugApi(libs.androidx.compose.ui.tooling)
    api(libs.androidx.appcompat)
    api(libs.androidx.material3)
    implementation(libs.androidx.material)

//  Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    ksp(libs.hilt.compiler)
    api(libs.hilt.navigation.compose)

//  Data Source
    api(libs.room.ktx)
    ksp(libs.room.compiler)
    api(libs.datastore.preferences)

//  Network
    api(libs.retrofit)
    api(libs.gson)
    api(libs.okhttp.logging)
}
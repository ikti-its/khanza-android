import java.util.Properties

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

        val properties = Properties()
        properties.load(project.rootProject.file("secrets.properties").inputStream())

        buildConfigField("boolean", "DEBUG", properties.getProperty("DEBUG", "false"))
        buildConfigField("String", "BASE_URL", properties.getProperty("BASE_URL"))
        resValue("string", "MAPS_API_KEY", properties.getProperty("MAPS_API_KEY"))
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
    api(libs.androidx.navigation.compose)

//  Permissions
    api(libs.accompanist.permissions)

//  Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    ksp(libs.hilt.compiler)
    api(libs.hilt.navigation.compose)

//  Data Source
    api(libs.room.ktx)
    ksp(libs.room.compiler)
    api(libs.datastore.preferences)

//  Maps
    api(libs.play.services.maps)
    api(libs.play.services.location)
    api(libs.maps.compose)

//  Network
    api(libs.retrofit)
    api(libs.gson)
    api(libs.okhttp.logging)
}
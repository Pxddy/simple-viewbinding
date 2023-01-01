import com.pxddy.simpleviewbinding.buildlogic.common.Version

plugins {
    id("simpleviewbinding.android.application")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.pxddy.simpleviewbinding.demo"

    defaultConfig {
        applicationId = "com.pxddy.simpleviewbinding.demo"
        versionCode = Version.Release.versionCode
        versionName = Version.Release.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(":library"))

    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.leakcanary)
    implementation(libs.material3)
    implementation(libs.bundles.androidx.navigation)
    implementation(libs.timber)
}
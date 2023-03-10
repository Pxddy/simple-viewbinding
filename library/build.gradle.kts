plugins {
    id("simpleviewbinding.android.library")
    id("maven-publish")
}

private val variantNameRelease = "release"

android {
    namespace = "com.pxddy.simpleviewbinding"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
    }

    publishing {
        singleVariant(variantNameRelease)
    }
}

dependencies {

    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.fragment.ktx)

    debugImplementation(libs.androidx.appcompat)

    androidTestImplementation(libs.kotest.assertions)

    androidTestImplementation(libs.junit4)

    androidTestImplementation(libs.androidx.fragment.testing)

    androidTestImplementation(libs.androidx.test.core.ktx)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.runner)
}

publishing {
    publications {
        register<MavenPublication>(variantNameRelease) {
            afterEvaluate {
                from(components[variantNameRelease])
            }
        }
    }
}

plugins {
    `kotlin-dsl`
}

group = "com.pxddy.simpleviewbinding.buildlogic"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(JavaVersion.VERSION_11.toString()))
    }
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "simpleviewbinding.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "simpleviewbinding.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
    }
}
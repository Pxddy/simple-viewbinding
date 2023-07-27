plugins {
    `kotlin-dsl`
}

group = "com.pxddy.simpleviewbinding.buildlogic"


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
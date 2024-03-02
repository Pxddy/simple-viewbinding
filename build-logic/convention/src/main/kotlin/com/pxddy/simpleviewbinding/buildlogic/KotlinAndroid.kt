package com.pxddy.simpleviewbinding.buildlogic

import com.android.build.api.dsl.CommonExtension
import com.pxddy.simpleviewbinding.buildlogic.common.Version
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = Version.Sdk.compile

        defaultConfig {
            minSdk = Version.Sdk.min
        }

        compileOptions {
            sourceCompatibility = Version.Java.version
            targetCompatibility = Version.Java.version
        }
    }

    configureKotlinJvm()
}

private fun Project.configureKotlinJvm() {
    extensions.configure<JavaPluginExtension> {
        sourceCompatibility = Version.Java.version
        targetCompatibility = Version.Java.version
    }

    configureKotlin()
}

private fun Project.configureKotlin() {
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = Version.Java.version.toString()

            freeCompilerArgs = freeCompilerArgs + listOf(
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-opt-in=kotlinx.coroutines.FlowPreview",
                "-opt-in=kotlin.time.ExperimentalTime",
                "-opt-in=kotlin.RequiresOptIn",
                "-opt-in=kotlin.ExperimentalStdlibApi"
            )
        }
    }
}
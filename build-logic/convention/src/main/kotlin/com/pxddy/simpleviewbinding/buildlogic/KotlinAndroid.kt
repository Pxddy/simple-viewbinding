package com.pxddy.simpleviewbinding.buildlogic

import com.android.build.api.dsl.CommonExtension
import com.pxddy.simpleviewbinding.buildlogic.common.Version
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *>,
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

        kotlinOptions {
            options.jvmTarget.set(Version.Java.jvmTarget)

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

fun CommonExtension<*, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}
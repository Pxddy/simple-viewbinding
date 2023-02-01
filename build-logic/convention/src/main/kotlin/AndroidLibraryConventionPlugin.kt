import com.android.build.api.dsl.LibraryExtension
import com.pxddy.simpleviewbinding.buildlogic.common.Version
import com.pxddy.simpleviewbinding.buildlogic.configureJava
import com.pxddy.simpleviewbinding.buildlogic.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = Version.Sdk.target
            }

            configureJava()
        }
    }
}
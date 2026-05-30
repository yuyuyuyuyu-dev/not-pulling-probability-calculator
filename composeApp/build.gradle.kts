import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.aboutLibraries)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.composePwa)
    alias(libs.plugins.spotless)
    alias(libs.plugins.detekt)
}

spotless {
    kotlin {
        target("**/*.kt")
        targetExclude("**/build/**/*.kt")
        ktlint()
    }
    kotlinGradle {
        target("*.gradle.kts")
        ktlint()
    }
}

detekt {
    source.setFrom(
        "src/commonMain/kotlin",
        "src/androidMain/kotlin",
        "src/jsMain/kotlin",
        "src/jvmMain/kotlin",
        "src/wasmJsMain/kotlin",
    )
    config.setFrom(rootProject.files("config/detekt/detekt.yml"))
    buildUponDefaultConfig = false
    allRules = false
    parallel = true
    basePath.set(rootDir)
    failOnSeverity = dev.detekt.gradle.extensions.FailOnSeverity.Warning
}

tasks.withType<dev.detekt.gradle.Detekt>().configureEach {
    jvmTarget.set("11")
    reports {
        sarif.required.set(true)
        markdown.required.set(true)
    }
    if (name == "detektDebugAndroid") {
        exclude(
            "**/Platform.android.kt",
            "**/di/AppComponent.android.kt",
            "**/androidMainResourceCollectors/**",
            "**/ActualResourceCollectors.kt",
            "**/ExpectResourceCollectors.kt",
        )
    }
    if (name == "detektMainJvm") {
        exclude(
            "**/Platform.jvm.kt",
            "**/di/AppComponent.jvm.kt",
            "**/jvmMainResourceCollectors/**",
            "**/ActualResourceCollectors.kt",
            "**/ExpectResourceCollectors.kt",
        )
    }
}

tasks.withType<dev.detekt.gradle.DetektCreateBaselineTask>().configureEach {
    jvmTarget.set("11")
}

val unusedCodeCheck by tasks.registering {
    group = "verification"
    description = "Runs detekt checks for unused production Kotlin code."
    dependsOn(
        "detekt",
        "detektCommonMainSourceSet",
        "detektWebMainSourceSet",
        "detektAndroidMainSourceSet",
        "detektJsMainSourceSet",
        "detektJvmMainSourceSet",
        "detektWasmJsMainSourceSet",
        "detektDebugAndroid",
        "detektMainJvm",
    )
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    js {
        browser()
        binaries.executable()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }

    jvm {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.kotlinResult)
            implementation(libs.aboutlibraries.compose.m3)
            implementation(libs.myMaterialTheme)
            implementation(libs.simpleTopAppBar)
            implementation(libs.jetbrains.navigation3.ui)
            implementation(libs.jetbrains.material3.adaptiveNavigation3)
            implementation(libs.jetbrains.lifecycle.viewmodelNavigation3)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlin.inject.runtime)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.compose.uiTest)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
        }
        jvmTest.dependencies {
            implementation(compose.desktop.currentOs)
        }
    }
}

android {
    namespace = "dev.yuyuyuyuyu.notpullingprobabilitycalculator"
    compileSdk =
        libs.versions.android.compileSdk
            .get()
            .toInt()

    defaultConfig {
        applicationId = "dev.yuyuyuyuyu.notpullingprobabilitycalculator"
        minSdk =
            libs.versions.android.minSdk
                .get()
                .toInt()
        targetSdk =
            libs.versions.android.targetSdk
                .get()
                .toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

compose.desktop {
    application {
        mainClass = "dev.yuyuyuyuyu.notpullingprobabilitycalculator.MainKt"
    }
}

dependencies {
    debugImplementation(libs.compose.uiTooling)
    add("kspCommonMainMetadata", libs.kotlin.inject.compiler)
    add("kspAndroid", libs.kotlin.inject.compiler)
    add("kspJs", libs.kotlin.inject.compiler)
    add("kspWasmJs", libs.kotlin.inject.compiler)
    add("kspJvm", libs.kotlin.inject.compiler)
}

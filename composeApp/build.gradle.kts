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
    // Start from detekt's bundled default rule set; config/detekt/detekt.yml only
    // records deltas (rules we turn off to defer to ktlint, plus Compose tweaks).
    config.setFrom(rootProject.files("config/detekt/detekt.yml"))
    buildUponDefaultConfig = true
    allRules = false
    parallel = true
    basePath = rootDir
}

tasks.withType<dev.detekt.gradle.Detekt>().configureEach {
    jvmTarget = "11"
    // Never lint generated sources (KSP output, compose resource accessors, etc.).
    // Their source roots live *under* build/, so a relative glob can't catch them;
    // filter by absolute path instead.
    val buildPath =
        layout.buildDirectory
            .get()
            .asFile.path
    exclude { it.file.path.startsWith(buildPath) }
    reports {
        sarif.required.set(true)
        markdown.required.set(true)
        html.required.set(true)
    }
}

tasks.withType<dev.detekt.gradle.DetektCreateBaselineTask>().configureEach {
    jvmTarget = "11"
}

// Aggregate task: runs detekt with type resolution for every compilation/target.
// Type resolution gives the fullest analysis (needed by several detekt rules and
// the Compose rules).
val detektAll by tasks.registering {
    group = "verification"
    description = "Runs detekt analysis (with type resolution) across all targets."
    dependsOn(
        "detektDebugAndroid",
        "detektMainJvm",
        "detektJsMainSourceSet",
        "detektWasmJsMainSourceSet",
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
        }
        jvmMain.dependencies {
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
    detektPlugins(libs.compose.rules.detekt)
    debugImplementation(libs.compose.uiTooling)
    add("kspCommonMainMetadata", libs.kotlin.inject.compiler)
    add("kspAndroid", libs.kotlin.inject.compiler)
    add("kspJs", libs.kotlin.inject.compiler)
    add("kspWasmJs", libs.kotlin.inject.compiler)
    add("kspJvm", libs.kotlin.inject.compiler)
}

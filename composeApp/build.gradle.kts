import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.aboutLibraries)
    alias(libs.plugins.ksp)
    alias(libs.plugins.composePwa)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.detekt)
}

ktlint {
    filter {
        val buildPath =
            layout.buildDirectory
                .get()
                .asFile.path
        exclude { it.file.path.startsWith(buildPath) }
    }
}

detekt {
    // Start from detekt's bundled default rule set; config/detekt/detekt.yml only
    // records deltas (rules we turn off to defer to ktlint, plus Compose tweaks).
    source.setFrom("src")
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
            implementation(libs.myComposables)
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
        providers
            .gradleProperty("sdk.compile")
            .get()
            .toInt()

    defaultConfig {
        applicationId = "dev.yuyuyuyuyu.notpullingprobabilitycalculator"
        minSdk =
            providers
                .gradleProperty("sdk.min")
                .get()
                .toInt()
        targetSdk =
            providers
                .gradleProperty("sdk.target")
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

aboutLibraries {
    export {
        outputFile = file("src/commonMain/composeResources/files/aboutlibraries.json")
    }
}

compose.resources {
    customDirectory(
        sourceSetName = "commonMain",
        directoryProvider =
            tasks
                .named("exportLibraryDefinitions")
                .map { layout.projectDirectory.dir("src/commonMain/composeResources") },
    )
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

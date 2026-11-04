import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.aboutLibraries)
    alias(libs.plugins.ksp)
    alias(libs.plugins.composePwa)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.detekt)
}

ktlint {
    version = libs.versions.ktlintCli.get()
    filter {
        val buildPath =
            layout.buildDirectory
                .get()
                .asFile.path
        exclude { it.file.path.startsWith(buildPath) }
    }
}

detekt {
    source.setFrom("src")
    config.setFrom(rootProject.files("config/detekt/detekt.yml"))
    buildUponDefaultConfig = true
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }

    sourceSets {
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
            implementation(libs.compose.uiTest)
        }
        wasmJsTest {
            kotlin.srcDir("src/specTest/kotlin")
        }
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

dependencies {
    add("kspWasmJs", libs.kotlin.inject.compiler)
}

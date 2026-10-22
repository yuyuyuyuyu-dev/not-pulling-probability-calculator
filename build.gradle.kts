plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.ktlint)
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.versionCatalogUpdate)
}

// Default pruning is left on: the catalog holds
// only versions referenced via version.ref, so the plugin will drop a version entry
// that nothing references anymore (build-config scalars live in gradle.properties).
// End-of-line comments are dropped on format, so annotate an entry on the line above.
versionCatalogUpdate {
    sortByKey = true
}

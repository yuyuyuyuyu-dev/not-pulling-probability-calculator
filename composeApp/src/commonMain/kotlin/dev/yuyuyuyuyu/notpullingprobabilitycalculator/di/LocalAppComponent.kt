package dev.yuyuyuyuyu.notpullingprobabilitycalculator.di

import androidx.compose.runtime.staticCompositionLocalOf

/**
 * Provides the app's DI container to the composition. Provided once at the top of the
 * tree (see App()), so any screen can resolve its own dependencies without the container
 * being threaded through intermediate composables as a parameter.
 */
val LocalAppComponent =
    staticCompositionLocalOf<AppComponent> {
        error("LocalAppComponent not provided. Wrap content in CompositionLocalProvider(LocalAppComponent provides …).")
    }

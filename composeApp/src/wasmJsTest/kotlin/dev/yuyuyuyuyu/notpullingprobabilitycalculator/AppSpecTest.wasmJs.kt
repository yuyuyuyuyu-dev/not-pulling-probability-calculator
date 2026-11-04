package dev.yuyuyuyuyu.notpullingprobabilitycalculator

import kotlin.js.ExperimentalWasmJsInterop

@OptIn(ExperimentalWasmJsInterop::class)
actual fun useEnglishInBrowser(): Unit =
    js(
        """
        Object.defineProperty(navigator, 'languages', { get: () => ['en-US'], configurable: true })
        """,
    )

@OptIn(ExperimentalWasmJsInterop::class)
actual fun restoreBrowserLanguage(): Unit = js("delete navigator.languages")

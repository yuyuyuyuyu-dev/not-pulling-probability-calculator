package dev.yuyuyuyuyu.notpullingprobabilitycalculator

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
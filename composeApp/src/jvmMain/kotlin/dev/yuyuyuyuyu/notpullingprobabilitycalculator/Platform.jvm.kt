package dev.yuyuyuyuyu.notpullingprobabilitycalculator

class JvmPlatform : Platform {
    override val name: String = "JVM ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = JvmPlatform()

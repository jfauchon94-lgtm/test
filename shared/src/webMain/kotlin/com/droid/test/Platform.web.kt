package com.droid.test

class WebPlatform : Platform {
    override val name: String = "Web (Kotlin/Wasm & JS)"
}

actual fun getPlatform(): Platform = WebPlatform()

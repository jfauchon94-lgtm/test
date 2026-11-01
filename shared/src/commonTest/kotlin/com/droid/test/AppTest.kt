package com.droid.test

import kotlin.test.Test
import kotlin.test.assertTrue

class AppTest {
    @Test
    fun testPlatformNameIsNotEmpty() {
        val platform = getPlatform()
        assertTrue(platform.name.isNotBlank(), "Platform name should not be blank")
    }
}

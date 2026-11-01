package com.droid.test

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    val state = rememberWindowState(
        size = DpSize(480.dp, 640.dp),
        position = WindowPosition.PlatformDefault,
    )
    Window(
        onCloseRequest = ::exitApplication,
        title = "test",
        state = state,
    ) {
        App()
    }
}

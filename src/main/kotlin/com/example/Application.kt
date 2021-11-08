package com.example

import com.example.config.initializeDb
import com.example.config.configureRouting
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        initializeDb()
    }.start(wait = true)
}

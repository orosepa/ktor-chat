package com.example

import com.example.config.initializeDb
import com.example.config.configureRouting
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, System.getenv("PORT").toInt()) {
        configureRouting()
        initializeDb()
    }.start(wait = true)
}

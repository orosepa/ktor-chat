package com.example.routes

import com.example.config.respondCss
import com.example.views.chatStylesheet
import io.ktor.application.*
import io.ktor.http.content.*
import io.ktor.routing.*

fun Routing.viewToolsRouting() {
    get("/styles.css") {
        call.respondCss { chatStylesheet() }
    }
    static("/scripts") {
        resources("scripts")
    }
}
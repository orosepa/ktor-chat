package com.example.plugins

import com.example.routes.chatRouting
import com.example.routes.creationRouting
import com.example.routes.mainRouting
import freemarker.cache.ClassTemplateLoader
import freemarker.core.HTMLOutputFormat
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.freemarker.*
import io.ktor.http.cio.websocket.*
import io.ktor.websocket.*
import java.time.Duration

fun Application.configureRouting() {

    install(WebSockets) {
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
        outputFormat = HTMLOutputFormat.INSTANCE
    }

    routing {
        mainRouting()
        creationRouting()
        chatRouting()
    }
}

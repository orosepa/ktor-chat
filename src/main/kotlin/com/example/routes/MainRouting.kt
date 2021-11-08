package com.example.routes

import com.example.models.Chat
import com.example.views.mainView
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

fun Route.mainRouting() {
    get("/") {
        call.respondHtml { mainView() }
    }
    post("/") {
        val chatId = call.receiveParameters()["chatId"]
        val chat = transaction {
            Chat.findById(UUID.fromString(chatId))
        }
        if (chat == null)
            call.respondRedirect("/")
        else
            call.respondRedirect("/chat/$chatId")
    }
}
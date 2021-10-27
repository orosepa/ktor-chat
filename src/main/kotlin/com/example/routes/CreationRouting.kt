package com.example.routes

import com.example.models.Chat
import com.example.views.creationView
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

fun Route.creationRouting() {
    var nChatUUID = ""

    get("/create") {
        transaction {
            val nChat = Chat.new {
                name = "Chat"
            }
            nChatUUID = nChat.id.toString()
        }
        call.respondHtml { creationView() }
    }
    post("/create") {
        val chatName = call.receiveParameters()["chatName"]
        transaction {
            Chat.findById(UUID.fromString(nChatUUID))?.name = chatName ?: "chat"
        }
        call.respondRedirect("/chat/${nChatUUID}")
    }
}
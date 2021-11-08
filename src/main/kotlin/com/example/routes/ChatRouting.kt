package com.example.routes

import com.example.config.UserObject
import com.example.models.Chat
import com.example.models.Message
import com.example.models.Messages
import com.example.views.loginView
import io.ktor.application.*
import io.ktor.freemarker.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

fun Routing.chatRouting() {

    route("/chat") {
        get ("/") {
            call.respondRedirect("/")
        }
        get("{id}") {

            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest )
            try {
                UserObject.chatId = UUID.fromString(id)
            } catch (e: IllegalArgumentException) {
                println(e.localizedMessage)
                return@get call.respondText(
                    "Missing or malformed id",
                    status = HttpStatusCode.BadRequest )
            }
            transaction {
                Chat.findById(UserObject.chatId!!)
            } ?: return@get call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest )

            if (UserObject.name == null) {
                return@get call.respondHtml { loginView() }
            }
            transaction {
                val dbMessages = Message.find { Messages.chat eq UserObject.chatId }
                UserObject.msgBuffer = dbMessages.toList()
                UserObject.chatName = Chat.findById(UserObject.chatId!!)?.name
            }

            call.respond(FreeMarkerContent("chat.ftl",
                mapOf("entries" to UserObject.msgBuffer,
                    "chat_name" to UserObject.chatName,
                    "chat_id" to UserObject.chatId.toString()),
                ""))
        }
        post("{id}") {
            UserObject.name = call.receiveParameters()["name"]
            return@post call.respondRedirect("/chat/${UserObject.chatId}")
        }
    }
}
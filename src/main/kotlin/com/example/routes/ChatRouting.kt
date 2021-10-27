package com.example.routes

import com.example.models.Chat
import com.example.models.Message
import com.example.models.Messages
import com.example.views.loginView
import io.ktor.application.*
import io.ktor.freemarker.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.websocket.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime
import java.util.*


data class Connection(val chatId: UUID?, val session: DefaultWebSocketSession, val name: String?)

fun Routing.chatRouting() {
    var chatId: UUID? = null
    var chatName: String? = null
    var name: String? = null
    var msgBuffer: List<Message> = mutableListOf()

    val connections = Collections.synchronizedSet<Connection?>(LinkedHashSet())

    route("/chat") {
        get ("/") {
            call.respondRedirect("/")
        }
        get("{id}") {

            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest )
            try {
                chatId = UUID.fromString(id)
            } catch (e: IllegalArgumentException) {
                println(e.localizedMessage)
                return@get call.respondText(
                    "Missing or malformed id",
                    status = HttpStatusCode.BadRequest )
            }
            transaction {
                Chat.findById(chatId!!)
            } ?: return@get call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest )

            if (name == null) {
                return@get call.respondHtml { loginView() }
            }
            transaction {
                val dbMessages = Message.find { Messages.chat eq chatId }
                msgBuffer = dbMessages.toList()
                chatName = Chat.findById(chatId!!)?.name
            }

            call.respond(FreeMarkerContent("chat.ftl",
                mapOf("entries" to msgBuffer, "chat_name" to chatName), ""))
        }
        post("{id}") {
            name = call.receiveParameters()["name"]
            return@post call.respondRedirect("/chat/$chatId")
        }
    }

    webSocket("/webs") {
        val thisConnection = Connection(chatId, this, name)
        println("Adding user $thisConnection")
        name = null
        connections += thisConnection
        try {
            for (frame in incoming) {
                frame as? Frame.Text ?: continue
                val receivedText = frame.readText()
                val newMessage = transaction {
                    Message.new {
                        chat = Chat.findById(chatId!!)!!
                        from = thisConnection.name!!
                        time = LocalDateTime.now()
                        text = receivedText
                    }
                }
                connections.forEach {
                    if (it.chatId == chatId)
                        it.session.send(newMessage.toJsonArray())
                }
            }
        } catch (e: Exception) {
            println(e.localizedMessage)
        } finally {
            println("Removing $thisConnection")
            connections -= thisConnection
        }
    }
}
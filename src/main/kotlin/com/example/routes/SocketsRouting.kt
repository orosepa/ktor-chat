package com.example.routes

import com.example.config.UserObject
import com.example.models.Chat
import com.example.models.Message
import io.ktor.http.cio.websocket.*
import io.ktor.routing.*
import io.ktor.websocket.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.Clock
import java.time.LocalDateTime
import java.util.*

data class Connection(val chatId: UUID?, val session: DefaultWebSocketSession, val name: String?)

fun Routing.configureWebsockets() {
    val connections = Collections.synchronizedSet<Connection?>(LinkedHashSet())

    webSocket("/webs") {
        val thisConnection = Connection(UserObject.chatId, this, UserObject.name)
        println("Adding user $thisConnection")
        UserObject.name = null
        connections += thisConnection
        try {
            for (frame in incoming) {
                frame as? Frame.Text ?: continue
                val receivedText = frame.readText()
                val newMessage = transaction {
                    Message.new {
                        chat = Chat.findById(UserObject.chatId!!)!!
                        from = thisConnection.name!!
                        time = LocalDateTime.now(Clock.systemUTC())
                        text = receivedText
                    }
                }
                connections.forEach {
                    if (it.chatId == UserObject.chatId)
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
package com.example.routes

import com.example.models.Chat
import com.example.models.Message
import io.ktor.http.cio.websocket.*
import io.ktor.routing.*
import io.ktor.websocket.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime
import java.util.*

//fun Routing.configureWebsockets {
//    val connections = Collections.synchronizedSet<Connection?>(LinkedHashSet())

//    webSocket("/webs") {
//        val thisConnection = Connection(chatId, this, name)
//        println("Adding user $thisConnection")
//        name = null
//        connections += thisConnection
//        try {
//            for (frame in incoming) {
//                frame as? Frame.Text ?: continue
//                val receivedText = frame.readText()
//                val newMessage = transaction {
//                    Message.new {
//                        chat = Chat.findById(chatId!!)!!
//                        from = thisConnection.name!!
//                        time = LocalDateTime.now()
//                        text = receivedText
//                    }
//                }
//                connections.forEach {
//                    if (it.chatId == chatId)
//                        it.session.send(newMessage.toJsonArray())
//                }
//            }
//        } catch (e: Exception) {
//            println(e.localizedMessage)
//        } finally {
//            println("Removing $thisConnection")
//            connections -= thisConnection
//        }
//    }
//}
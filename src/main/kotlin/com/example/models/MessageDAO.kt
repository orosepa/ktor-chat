package com.example.models

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Messages : IntIdTable() {
    val chat = reference("chat", Chats)
    val from: Column<String> = varchar("from", 50)
    val time: Column<LocalDateTime> = datetime("time")
    val text: Column<String> = text("text")
}

class Message(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Message>(Messages)

    var chat by Chat referencedOn Messages.chat
    var from by Messages.from
    var time by Messages.time
    var text by Messages.text


    fun getFormattedTime(): String {
        return time.format(DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm"))
    }

    fun toJsonArray(): String {
        return "[\"$from\", \"${getFormattedTime()}\", \"$text\"]"
    }
}

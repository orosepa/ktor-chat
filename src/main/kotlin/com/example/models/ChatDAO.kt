package com.example.models

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import java.util.*

object Chats : UUIDTable() {
    val name: Column<String> = varchar("name", 50)
}

class Chat(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<Chat>(Chats)
    var name by Chats.name
}
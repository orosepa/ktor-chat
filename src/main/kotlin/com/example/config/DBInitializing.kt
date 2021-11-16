package com.example.config

import com.example.models.Chats
//import com.example.models.Connections
import com.example.models.Messages
import io.ktor.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.initializeDb() {
    Database.connect(url = "jdbc:postgresql://${DBInfo.HOST}:${DBInfo.PORT}/${DBInfo.DATABASE}",
        driver = "org.postgresql.Driver",
        user = DBInfo.USER,
        password = DBInfo.PASSWORD)

    transaction {
        addLogger(StdOutSqlLogger)
        SchemaUtils.create(Chats, Messages)
    }
}
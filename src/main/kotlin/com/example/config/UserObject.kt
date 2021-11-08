package com.example.config

import com.example.models.Message
import java.util.*

object UserObject {
    var chatId: UUID? = null
    var chatName: String? = null
    var name: String? = null
    var msgBuffer: List<Message> = mutableListOf()
}
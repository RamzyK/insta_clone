package com.example.instaclone.data.model.messages

data class Message(
    val convId: String,
    val messages: List<MessageX>,
    val participants: List<String>
)
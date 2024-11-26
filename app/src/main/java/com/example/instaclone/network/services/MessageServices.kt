package com.example.instaclone.network.services

import com.example.instaclone.network.dto.messages_dto.MessageDto
import retrofit2.Call
import retrofit2.http.GET

interface MessageServices {


    // Fonction représentant le contrat d'interface avec l'API

    @GET("RamzyK/demo/messages") // FONCTION + PATH
    fun getAllMessages(): Call<List<MessageDto>>
}
package com.example.instaclone.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.instaclone.data.model.messages.Message
import com.example.instaclone.network.RetrofitClient
import com.example.instaclone.network.dto.messages_dto.MessageDto
import com.example.instaclone.network.mapper.mapMessageDtoToMessageModel
import com.example.instaclone.network.services.MessageServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MessagesRepository {

    private val messagesServices = RetrofitClient.instance.create(MessageServices::class.java)

    private val _messagesData = MutableLiveData<List<Message>>()
    val messagesData: LiveData<List<Message>> get() = _messagesData


    fun getAllMessages() {
        val call = this.messagesServices.getAllMessages()

        call.enqueue(object: Callback<List<MessageDto>>{
            override fun onResponse(
                call: Call<List<MessageDto>>,
                response: Response<List<MessageDto>>
            ) {
                val body = response.body()

                this@MessagesRepository._messagesData.value = body?.map {
                    mapMessageDtoToMessageModel(it)
                    // Implementer une logique de sauvegarde des données avant de renvoyer la donnée à la vue
                }
            }

            override fun onFailure(call: Call<List<MessageDto>>, t: Throwable) {
                Log.d("Error getAllMessages()", "Error: ${t.message}")
            }

        })
    }


}
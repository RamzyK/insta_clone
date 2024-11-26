package com.example.instaclone.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.instaclone.data.model.messages.Message
import com.example.instaclone.repositories.MessagesRepository

class MessagesViewModel(
    val messageRepo: MessagesRepository,
    val context: LifecycleOwner
): ViewModel() {

    // Observable pour la vue
    private val _messagesData = MutableLiveData<List<Message>>()
    val messagesData: LiveData<List<Message>> get() = _messagesData

    fun fetchMessages() {
        this.messageRepo.messagesData.observe(this.context) { messages ->
            _messagesData.value = messages
        }

        this.messageRepo.getAllMessages()
    }


}
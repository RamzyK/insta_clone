package com.example.instaclone

import com.example.instaclone.network.dto.messages_dto.MessageDto
import com.example.instaclone.network.dto.messages_dto.MessageXDto
import com.example.instaclone.network.services.MessageServices
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Call

class DirectMessageServiceTest {

    // Initialiser les mocks
    @Mock
    private lateinit var mockMessageCall: Call<List<MessageDto>>

    @Mock
    private lateinit var mockMessagesService: MessageServices


    // Setup mockito
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }


    // Tester la récupération des données mockées depuis un service


    // Creer la donnée simulée
    private fun generateListOfMockedMessages(totalConversationCount: Int, maxMessagesPerConv: Int): List<MessageDto> {
        // Créer "totalConversationCount" conversation contenant chacune "maxMessagesPerConv" messages
        // En utilisant les DTO "MessageDto" & "MessageXDto"

        // generateMockedMessageList() 


        return listOf()
    }


    private fun generateMockedMessageList(size: Int): List<MessageXDto> {
        // Créer une liste de taille entre 0 et size (aléatoirement) et la retourner
        return listOf()
    }

}
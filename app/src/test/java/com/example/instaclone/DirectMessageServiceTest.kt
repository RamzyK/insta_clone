package com.example.instaclone

import com.example.instaclone.network.dto.messages_dto.MessageDto
import com.example.instaclone.network.dto.messages_dto.MessageXDto
import com.example.instaclone.network.services.MessageServices
import com.example.instaclone.repositories.MessagesRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Response
import org.mockito.Mockito.`when`
import org.junit.Assert.assertTrue
import kotlin.random.Random

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
    @Test
    fun `getAllMessages should return a non empty list of MessageDto objects` () {
        // Préparation
        val mockedResponseBody: List<MessageDto> = generateListOfMockedMessages(10, 20)

        // Simulation d'un call HTTP (Mockito)
        // If this func call then return this
        // when(mockMessageCall.execute()).then-return(Response.success(mockedResponseBody))

        // Observe le trigger call sur la résponse du service
        `when`(mockMessageCall.execute()).thenReturn(Response.success(mockedResponseBody))

        // Observe le trigger call sur le service
        `when`(mockMessagesService.getAllMessages()).thenReturn(mockMessageCall)

        // Appel du service pour récupérer tous les message
        val response =  mockMessagesService.getAllMessages().execute()

        // Utilise la donnée pour le test

        // Verification du resultat
        assertTrue(response.isSuccessful)
    }

    // Creer la donnée simulée
    private fun generateListOfMockedMessages(totalConversationCount: Int, maxMessagesPerConv: Int): List<MessageDto> {
        // Créer "totalConversationCount" conversation contenant chacune "maxMessagesPerConv" messages
        // En utilisant les DTO "MessageDto" & "MessageXDto"
        // generateMockedMessageList()
        val conversations = mutableListOf<MessageDto>()

        for (convPosition in 0..totalConversationCount) {
            conversations.add(
                MessageDto(
                    convId = "conv$convPosition",
                    messages = generateMockedMessageList(Random.nextInt(maxMessagesPerConv)),
                    listOf("Toto", "Titi")
                )
            )
        }
        return conversations
    }


    private fun generateMockedMessageList(size: Int): List<MessageXDto> {
        // Créer une liste de taille entre 0 et size (aléatoirement) et la retourner
        val messages = mutableListOf<MessageXDto>()

        for (i in 0..size) {
            messages.add(
                MessageXDto(
                    "Message-$i",
                    "Sender-$i",
                    "00:00:00"
                )
            )
        }

        return messages
    }

}
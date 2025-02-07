package com.example.instaclone

import android.util.Log
import com.example.instaclone.network.dto.messages_dto.MessageDto
import com.example.instaclone.network.dto.messages_dto.MessageXDto
import com.example.instaclone.network.mapper.mapMessageDtoToMessageModel
import com.example.instaclone.network.services.MessageServices
import org.junit.Before
import retrofit2.Call
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.MockitoAnnotations
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random

class DirectMessageSeviceTest {

    // Declaration des mocks
    @Mock
    private lateinit var mockMessageCall: Call<List<MessageDto>>

    @Mock
    private lateinit var mockMessageServices: MessageServices


    // Setup Mockito
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    // Test mocké
    @Test
    fun `Test should have an equal size Message mapped list from MessageDto mock`() {
        // Préparation
        val mockedResponseBody: List<MessageDto> = generateListOfMockedMessages(10, 20)

        // Configuration Mockito pour réagir à l'appel d'un service
        // If this func call ---> then return this result

        // If mockMessageCall called --> return Response (mockedResponseBody)
        `when`(mockMessageCall.execute()).thenReturn(Response.success(mockedResponseBody))

        // If mockMessageServices.getAllMessages() called --> return mockedResponseBody
        `when`(mockMessageServices.getAllMessages()).thenReturn(mockMessageCall)



        // Simulation d'un call HTTP (Mockito)
        //val response = mockMessageServices.getAllMessages().execute()

        val call = mockMessageServices.getAllMessages()

        // Execution du call
        call.enqueue(object: Callback<List<MessageDto>> {
            override fun onResponse(
                call: Call<List<MessageDto>>,
                response: Response<List<MessageDto>>
            ) {
                val body = response.body()

                val mappedData = body?.map {
                    mapMessageDtoToMessageModel(it)
                    // Implementer une logique de sauvegarde des données avant de renvoyer la donnée à la vue
                } ?: listOf()

                // Verification du resultat
                assertEquals(mappedData.size, mockedResponseBody.size) // on verifie que le mapper a correctement fait le travail
            }

            override fun onFailure(call: Call<List<MessageDto>>, t: Throwable) {
                Log.d("Error getAllMessages()", "Error: ${t.message}")
            }

        })
    }



    // Simulation de la donnée utilisée dans le test
    fun generateListOfMockedMessages(total: Int, maxMessagesPerConv: Int): List<MessageDto> {
        // Générer une liste de MessageDto contenant chacun une liste de MessageXDto
        // generateMockedMessageX(Random size entre 0..size)

        val conversations = mutableListOf<MessageDto>()

        for (id in 0..total) {
            conversations.add(
                MessageDto(
                    convId = "conversation-$id",
                    messages = generateMockedMessageX(Random.nextInt(maxMessagesPerConv)),
                    listOf("Toto", "Titi")
                )
            )
        }

        return conversations
    }

    private fun generateMockedMessageX(size: Int) : List<MessageXDto> {
        val messages = mutableListOf<MessageXDto>()

        for (i in 0..size) {
            messages.add(
                MessageXDto(
                    message = "Message n°$i",
                    sender_id = "message_sender_$i",
                    timestamp = "00:00:00"
                )
            )
        }

        return messages
    }
}
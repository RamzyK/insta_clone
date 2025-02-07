package com.example.instaclone

import com.example.instaclone.network.dto.messages_dto.MessageDto
import com.example.instaclone.network.dto.messages_dto.MessageXDto
import com.example.instaclone.network.services.MessageServices
import org.junit.Before
import retrofit2.Call
import org.mockito.Mock
import org.mockito.MockitoAnnotations

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
    fun `Test should have an equal size Message mapped list from MessageDto mock`() {

    }



    // Simulation de la donnée utilisée dans le test
    fun generateListOfMockedMessages(total: Int, maxMessagesPerConv: Int): List<MessageDto> {
        // Générer une liste de MessageDto contenant chacun une liste de MessageXDto
        // generateMockedMessageX(Random size entre 0..size)

        return listOf()
    }

    private fun generateMockedMessageX(size: Int) : List<MessageXDto> {

        return listOf()
    } 

}
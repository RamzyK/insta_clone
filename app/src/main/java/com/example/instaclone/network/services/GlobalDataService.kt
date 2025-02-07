package com.example.instaclone.network.services

import com.example.instaclone.network.dto.GlobalModelDto
import retrofit2.Call
import retrofit2.http.GET

interface GlobalDataService {


    @GET("RamzyK/demo/db")
    fun getAllData(): Call<GlobalModelDto>
}
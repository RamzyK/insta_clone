package com.example.instaclone.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    private val BASE_URL = "https://my-json-server.typicode.com/"

    val okHttpClient = OkHttpClient.Builder().build()

    var instance: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}
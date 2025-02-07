package com.example.instaclone.di

import com.example.instaclone.network.services.GlobalDataService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val remoteModule = module {

    single() {
        // Inject OkHttpClient
        createOkHttpClient()
    }

    single {
        // Inject retrofit client
        createRetrofitClient(get(), BASE_URL)
    }

    single {
        // Inject GlobalDataService
        get<Retrofit>().create(GlobalDataService::class.java)
    }
}

fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().build()
}

fun createRetrofitClient(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}


private val BASE_URL = "https://my-json-server.typicode.com/"
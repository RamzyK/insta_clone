package com.example.instaclone.di


import com.example.instaclone.network.services.GlobalDataService
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val remoteModule = module {

    single { createOkHttpClient() } // Singleton

    // Retrofit instance
    single(named(fakeApiServerRetrofitClient)) {
        createRetrofitClient(get(), BASE_URL)
    }

    // Inject retrofitClient.instance.monService
    single {
        get<Retrofit>(
            named(fakeApiServerRetrofitClient) // On récupère l'instance de du client Retrofit injectée
        ).create(GlobalDataService::class.java) // On injecte notre service
    }
}

fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .readTimeout(20, TimeUnit.SECONDS)
        //.interceptors()
        .build()
}

fun createRetrofitClient(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
    return  Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}


const  val BASE_URL = "https://my-json-server.typicode.com/"
const val fakeApiServerRetrofitClient = "fakeApiServerRetrofitClient"
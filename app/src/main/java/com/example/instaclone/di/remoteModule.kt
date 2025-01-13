package com.example.instaclone.di


import com.example.instaclone.network.services.GlobalDataService
import org.koin.dsl.module

val remoteModule = module {

    single {
        // Retrofit instance
    }

    single {
        // Inject retrofitClient.instance.monService
    }
}
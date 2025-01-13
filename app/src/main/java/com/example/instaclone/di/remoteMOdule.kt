package com.example.instaclone.di

import org.koin.dsl.module

val remoteModule = module {

    single {
        // Inject retrofit client
    }

    single {
        // Inject OkHttpClient
    }

    single {
        // Inject GlobalDataService
    }
}
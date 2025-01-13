package com.example.instaclone.di

import com.example.instaclone.repositories.GlobalDataRepository
import com.example.instaclone.viewmodel.HomeFeedViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    // create a Presenter instance with injection of R.string.mystring resources from Android
   viewModelOf(::HomeFeedViewModel)

    single {
        //      Context()
        //     this@MainApplication
        GlobalDataRepository(get(), get())
    }
}
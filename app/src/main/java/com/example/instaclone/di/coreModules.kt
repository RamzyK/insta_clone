package com.example.instaclone.di

import com.example.instaclone.repositories.GlobalDataRepository
import com.example.instaclone.viewmodel.HomeFeedViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val coreModule = module {

    viewModel {
        HomeFeedViewModel(get(), get())
    }

    single {
        GlobalDataRepository(get(), get())
    }
}
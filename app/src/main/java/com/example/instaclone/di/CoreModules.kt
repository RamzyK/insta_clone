package com.example.instaclone.di

import com.example.instaclone.repositories.GlobalDataRepository
import com.example.instaclone.viewmodel.HomeFeedViewModel
import com.example.instaclone.views.pager_fragments.UserFeedFragment
import org.koin.androidx.fragment.dsl.fragment
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    fragment { UserFeedFragment() }

    // create a Presenter instance with injection of R.string.mystring resources from Android
   viewModel {
       HomeFeedViewModel(get<UserFeedFragment>(), get())
   }

    single {
        //      Context()
        //     this@MainApplication
        GlobalDataRepository(get(), get())
    }
}
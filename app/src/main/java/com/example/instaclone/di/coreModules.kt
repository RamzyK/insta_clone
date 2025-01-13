package com.example.instaclone.di

import com.example.instaclone.repositories.GlobalDataRepository
import com.example.instaclone.viewmodel.HomeFeedViewModel
import com.example.instaclone.views.pager_fragments.DirectMessagesFragment
import com.example.instaclone.views.pager_fragments.UserFeedFragment
import org.koin.androidx.fragment.dsl.fragment
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val coreModule = module {

    fragment { UserFeedFragment() }

    viewModel {
        HomeFeedViewModel(get(), get<UserFeedFragment>())
    }

    single {
        GlobalDataRepository(get(), get())
    }
}
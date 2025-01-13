package com.example.instaclone

import android.app.Application
import com.example.instaclone.di.appModule
import com.example.instaclone.di.remoteModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {

            // Reference Android context
            androidContext(this@MainApplication)

            // Load modules
            modules(listOf(
                appModule,
                remoteModule
            ))
        }
    }
}
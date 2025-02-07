package com.example.instaclone

import android.app.Application
import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.example.instaclone.di.appModule
import com.example.instaclone.di.remoteModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {

            // Reference Android context
            androidContext(this@MainApplication)

            // Load modules
            modules(appModules)
        }
    }
}

var appModules = mutableListOf(appModule, remoteModule)
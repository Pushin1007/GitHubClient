package com.pd.githubclient

import android.app.Application
import android.content.Context
import com.pd.githubclient.data.repository.Repository
import com.pd.githubclient.data.repository.RepositoryImpl
import com.pd.githubclient.didag.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    val di by lazy {
        DaggerAppComponent.builder().build()
    }
}

val Context.app: App
    get() = applicationContext as App
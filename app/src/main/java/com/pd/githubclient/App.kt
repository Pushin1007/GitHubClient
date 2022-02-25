package com.pd.githubclient

import android.app.Application
import android.content.Context
import com.pd.githubclient.data.repository.Repository
import com.pd.githubclient.data.repository.RepositoryImpl
import com.pd.githubclient.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    // создаем отдельную от koin зависимость. Исключительно для эксперимента.

    val repo : Repository by lazy{RepositoryImpl()}

    override fun onCreate() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(listOf(appModule))
        }
        super.onCreate()
    }

}
// создаем экстеншен для доступа к App
val Context.app: App
    get() = applicationContext as App
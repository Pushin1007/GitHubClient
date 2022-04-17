package com.pd.githubclient

import android.app.Application
import android.content.Context
import com.pd.githubclient.data.repository.Repository
import com.pd.githubclient.data.repository.RepositoryImpl
import com.pd.githubclient.didag.AppModule
import com.pd.githubclient.dikoin.AnnotationModule
import com.pd.githubclient.dikoin.appModule
import com.pd.githubclient.dikoin.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.ksp.generated.module

class App : Application() {
    /*
    //Dagger2
        val di by lazy {
            DaggerAppComponent.builder().build()
        }
     */

    //Koin
    override fun onCreate() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(appModule, viewModelModule, AnnotationModule().module)
        }
        super.onCreate()
    }

}



val Context.app: App
    get() = applicationContext as App
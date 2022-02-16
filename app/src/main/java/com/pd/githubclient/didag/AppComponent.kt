package com.pd.githubclient.didag

import com.pd.githubclient.ui.main.MainFragment
import dagger.Component
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(mainFragment: MainFragment)


}
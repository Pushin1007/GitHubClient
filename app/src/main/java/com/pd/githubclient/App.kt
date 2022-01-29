package com.pd.githubclient

import android.app.Application

import com.pd.githubclient.domain.repository.Repository
import com.pd.githubclient.domain.repository.RepositoryImpl

class App : Application() {
    val repositoryImpl: Repository by lazy { RepositoryImpl() }
}


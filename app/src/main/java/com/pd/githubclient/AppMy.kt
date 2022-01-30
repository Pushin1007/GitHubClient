package com.pd.githubclient

import android.app.Application

import com.pd.githubclient.domainMy.repository.Repository
import com.pd.githubclient.domainMy.repository.RepositoryImpl

class AppMy : Application() {
    val repositoryImpl: Repository by lazy { RepositoryImpl() }
}


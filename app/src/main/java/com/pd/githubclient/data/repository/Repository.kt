package com.pd.githubclient.data.repository

import com.pd.githubclient.domain.User
import org.koin.core.annotation.Single


interface Repository {
    fun getUserFromLocalStorage(): List<User>
}
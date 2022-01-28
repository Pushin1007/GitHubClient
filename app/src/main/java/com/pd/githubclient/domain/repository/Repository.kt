package com.pd.githubclient.domain.repository

import com.pd.githubclient.domain.entities.User

interface Repository {
    fun getUserFromLocalStorage(): List<User>
}
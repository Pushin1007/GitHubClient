package com.pd.githubclient.domain.repository

import com.pd.githubclient.domain.entities.User

class RepositoryImpl : Repository {
    override fun getUserFromLocalStorage() = User.getUsers()
}
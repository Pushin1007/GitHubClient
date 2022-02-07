package com.pd.githubclient.domain.repository

import com.pd.githubclient.data.User

class RepositoryImpl : Repository {
    override fun getUserFromLocalStorage(): List<User> {
        return User.getUsers()
    }
}
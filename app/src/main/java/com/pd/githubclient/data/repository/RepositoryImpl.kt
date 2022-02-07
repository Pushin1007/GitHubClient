package com.pd.githubclient.data.repository

import com.pd.githubclient.domain.User

class RepositoryImpl : Repository {
    override fun getUserFromLocalStorage(): List<User> {
        return User.getUsers()
    }
}
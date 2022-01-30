package com.pd.githubclient.domainMy.repository

import com.pd.githubclient.domainMy.entities.User

class RepositoryImpl : Repository {
    override fun getUserFromLocalStorage() = User.getUsers()
}
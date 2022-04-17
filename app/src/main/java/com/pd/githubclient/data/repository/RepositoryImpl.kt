package com.pd.githubclient.data.repository

import com.pd.githubclient.domain.User
import org.koin.core.annotation.Single

@Single // для добавления в коин через аннотацию

class RepositoryImpl : Repository {
    override fun getUserFromLocalStorage(): List<User> {
        return User.getUsers()
    }
}
package com.pd.githubclient.domainMy.repository

import com.pd.githubclient.domainMy.entities.User

interface Repository {
    fun getUserFromLocalStorage(): List<User>
}
package com.pd.githubclient.domain.repository

import com.pd.githubclient.data.User


interface Repository {
    fun getUserFromLocalStorage(): List<User>
}
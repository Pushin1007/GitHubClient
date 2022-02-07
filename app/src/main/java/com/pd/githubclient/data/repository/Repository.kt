package com.pd.githubclient.data.repository

import com.pd.githubclient.domain.User


interface Repository {
    fun getUserFromLocalStorage(): List<User>
}
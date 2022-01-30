package com.pd.githubclient

import com.pd.githubclient.domainMy.entities.User

sealed class AppState {
    data class Success(val userData: List<User>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
package com.pd.githubclient

import com.pd.githubclient.domain.User

sealed class AppState {
    /*
     Запечатанный класс который расширяют следующие наследники.
    Некоторая замена Enum и плюс  если мы хотим иметь возможность работы с состояниями приложения,
     и в это состояние иметь возможности передавать данные
     */

    data class Success(val userData: List<User>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
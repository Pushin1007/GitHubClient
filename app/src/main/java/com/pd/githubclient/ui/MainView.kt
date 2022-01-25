package com.pd.githubclient.ui

import com.pd.githubclient.domain.RepositoryEntity

interface MainView {
    fun showProgress() // презентер управляет вью
    fun showResult(repositories: RepositoryEntity) // загружаем список репозиториев
    fun showError(throwable: Throwable) // сообщаем об ошибке
}
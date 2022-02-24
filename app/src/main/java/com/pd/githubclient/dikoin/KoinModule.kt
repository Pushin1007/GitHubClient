package com.pd.githubclient.dikoin

import com.pd.githubclient.data.GitHubLoader
import com.pd.githubclient.data.repository.Repository
import com.pd.githubclient.data.repository.RepositoryImpl
import com.pd.githubclient.domain.ProfileRepositoryEntity
import com.pd.githubclient.ui.detail.DetailsFragmentViewModel
import com.pd.githubclient.ui.main.MainFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    //создаем синглтоны один на все приложение
    single<Repository> {
        RepositoryImpl()
    }

    single<GitHubLoader> {
        GitHubLoader()
    }

    single<ProfileRepositoryEntity> {
        ProfileRepositoryEntity()
    }

//View models
    // метод  (get() заставит обратиться коин и найти у своих модулей тип который нужно на вход во ViewModel

    viewModel<MainFragmentViewModel> {
        MainFragmentViewModel(get(), get(), get())
    }

    viewModel<DetailsFragmentViewModel> {
        DetailsFragmentViewModel(get(), get())
    }
}
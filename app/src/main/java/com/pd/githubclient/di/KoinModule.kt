package com.pd.githubclient.di


import com.pd.githubclient.data.ProfileRepositoryEntity
import com.pd.githubclient.domain.GitHubLoader
import com.pd.githubclient.domain.repository.Repository
import com.pd.githubclient.domain.repository.RepositoryImpl
import com.pd.githubclient.ui.main.MainFragmentViewModel
import com.pd.githubclient.ui.detail.DetailsFragmentViewModel


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

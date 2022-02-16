package com.pd.githubclient.diKoin


import com.pd.githubclient.app
import com.pd.githubclient.domain.ProfileRepositoryEntity
import com.pd.githubclient.data.GitHubLoader
import com.pd.githubclient.ui.main.MainFragmentViewModel
import com.pd.githubclient.ui.detail.DetailsFragmentViewModel
import org.koin.android.ext.koin.androidApplication


import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    //создаем синглтоны один на все приложение

    /* временно выключаю данный репозиторий из koin. буду экспериментировать на нем
    single<Repository> {
        RepositoryImpl()
    }
     */


    single<GitHubLoader> {
        GitHubLoader()
    }

    single<ProfileRepositoryEntity> {
        ProfileRepositoryEntity()
    }

//View models
    // метод  (get() заставит обратиться коин и найти у своих модулей тип который нужно на вход во ViewModel
/*
viewModel<MainFragmentViewModel> {
        MainFragmentViewModel(get(), get(), get())
    }
 */
    /*
    т.к. MainFragmentViewModel создается  в koin то зависимоть передаю на вход в конструктор здесь. и Следовательно здесь использую контекст коина.
     Если бы вьюмодель создавалась в MainFragment, то зависимость передавалась бы  там и использовался бы контекст фрагмента.
     */

    viewModel<MainFragmentViewModel> { // передаем в конструктор вьюмодель зависимость из app
        MainFragmentViewModel(get(), androidApplication().applicationContext.app.repo, get())
    }


    viewModel<DetailsFragmentViewModel> {
        DetailsFragmentViewModel(get(), get())
    }
}

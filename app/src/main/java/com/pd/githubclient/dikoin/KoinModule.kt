package com.pd.githubclient.dikoin

import com.pd.githubclient.data.GitHubLoader
import com.pd.githubclient.data.repository.Repository
import com.pd.githubclient.data.repository.RepositoryImpl
import com.pd.githubclient.domain.ProfileRepositoryEntity
import com.pd.githubclient.ui.detail.DetailsFragmentViewModel
import com.pd.githubclient.ui.main.MainFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.dsl.module

// Аннотация- добавляем модуль который занимается сканированием своих аннотаций по классам
@Module
@ComponentScan("com.pd.githubclient")
class AnnotationModule

//создаем синглтоны один на все приложение в одном модуле
val appModule = module {
/*
//выключем отсюда и добавляем через  аннотацию
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

}

//View models в другом модуле
val viewModelModule = module {

    // метод  (get() заставит обратиться коин и найти у своих модулей тип который нужно на вход во ViewModel

    viewModel<MainFragmentViewModel> {
        MainFragmentViewModel(get(), get(), get())
    }

    viewModel<DetailsFragmentViewModel> {
        DetailsFragmentViewModel(get(), get())
    }
}
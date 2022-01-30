package com.pd.githubclient.di

import com.pd.githubclient.data.GitNamesRepo
import com.pd.githubclient.data.LoadedProfileRepository
import com.pd.githubclient.domain.GitHubLoader
import com.pd.githubclient.ui.HomeFragmentViewModel
import com.pd.githubclient.ui.ProfileDetailsFragmentViewModel


import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<GitNamesRepo> {
        GitNamesRepo()
    }

    single<GitHubLoader> {
        GitHubLoader()
    }

    single<LoadedProfileRepository> {
        LoadedProfileRepository()
    }



    viewModel<HomeFragmentViewModel> {
        HomeFragmentViewModel(get(), get(), get())
    }

    viewModel<ProfileDetailsFragmentViewModel> {
        ProfileDetailsFragmentViewModel(get(), get())
    }
}

package com.pd.githubclient.didag

import com.pd.githubclient.data.ProfileRepositoryEntity
import com.pd.githubclient.domain.GitHubLoader
import com.pd.githubclient.domain.repository.Repository
import com.pd.githubclient.domain.repository.RepositoryImpl
import com.pd.githubclient.ui.detail.DetailsFragmentViewModel
import com.pd.githubclient.ui.main.MainFragmentViewModel
import dagger.Provides

class AppModule {
    @Provides
    fun provideRepository(): Repository {
        return RepositoryImpl()
    }

    @Provides
    fun provideGitHubLoader(): GitHubLoader {
        return GitHubLoader()
    }

    @Provides
    fun provideProfileRepositoryEntity(): ProfileRepositoryEntity {
        return ProfileRepositoryEntity()
    }

    @Provides
    fun provideMainFragmentViewModel(
        loader: GitHubLoader,
        repository: Repository,
        cacheRepository: ProfileRepositoryEntity
    ): MainFragmentViewModel {
        return MainFragmentViewModel(
            loader, repository, cacheRepository
        )
    }

    @Provides
    fun provideDetailsFragmentViewModel(
        cacheRepository: ProfileRepositoryEntity,
        loader: GitHubLoader
    ): DetailsFragmentViewModel {
        return DetailsFragmentViewModel(cacheRepository, loader)
    }
}
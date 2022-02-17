package com.pd.githubclient.didag

import com.pd.githubclient.data.GitHubLoader
import com.pd.githubclient.data.repository.Repository
import com.pd.githubclient.data.repository.RepositoryImpl
import com.pd.githubclient.domain.ProfileRepositoryEntity
import com.pd.githubclient.ui.detail.DetailsFragmentViewModel
import com.pd.githubclient.ui.main.MainFragmentViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideRepository(): Repository {
        return RepositoryImpl()
    }

    @Singleton
    @Provides
    fun provideGitHubLoader(): GitHubLoader {
        return GitHubLoader()
    }
    @Singleton
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
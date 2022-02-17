package com.pd.githubclient.didag

import com.pd.githubclient.data.GitHubLoader
import com.pd.githubclient.data.repository.Repository
import com.pd.githubclient.data.repository.RepositoryImpl
import com.pd.githubclient.domain.ProfileRepositoryEntity
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepModule {
    @Singleton
    @Provides
    fun provideRepository(): Repository {
        return RepositoryImpl()
    }

    @Singleton
    @Provides
    fun provideProfileRepositoryEntity(): ProfileRepositoryEntity {
        return ProfileRepositoryEntity()
    }
}
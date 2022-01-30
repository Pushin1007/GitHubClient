package com.pd.githubclient.ui

import com.pd.githubclient.data.LoadedProfileEntity
import com.pd.githubclient.domain.GitHubRepoEntity

data class DataDetailResponse(
    val repositories: List<GitHubRepoEntity>,
    val profile : LoadedProfileEntity,
    val isSuccess: Boolean
)
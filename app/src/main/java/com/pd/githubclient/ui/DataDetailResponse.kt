package com.pd.githubclient.ui

import com.pd.githubclient.data.ProfileEntity
import com.pd.githubclient.domain.GitHubRepoEntity

data class DataDetailResponse(
    val repositories: List<GitHubRepoEntity>,
    val profile : ProfileEntity,
    val isSuccess: Boolean
)
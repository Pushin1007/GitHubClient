package com.pd.githubclient.ui

import com.pd.githubclient.domain.ProfileEntity
import com.pd.githubclient.data.GitHubRepoEntity

data class DataDetailResponse(
    val repositories: List<GitHubRepoEntity>,
    val profile: ProfileEntity?
)
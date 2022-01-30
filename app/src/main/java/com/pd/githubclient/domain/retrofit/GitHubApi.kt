package com.pd.githubclient.domain.retrofit

import com.pd.githubclient.data.ProfileEntity
import com.pd.githubclient.domain.GitHubRepoEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {

    @GET("/users/{userName}")
    fun loadUserByName(
        @Path("userName") userName: String,
    ): Call<ProfileEntity>

    @GET("/users/{userName}/repos")
    fun loadUsersRepositories(
        @Path("userName") userName: String,
    ): Call<List<GitHubRepoEntity>>
}
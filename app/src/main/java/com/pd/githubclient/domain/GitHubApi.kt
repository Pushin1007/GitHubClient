package com.pd.githubclient.domain

import com.pd.githubclient.data.LoadedProfileEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {

    @GET("/users/{userName}")
    fun loadUserByName(
        @Path("userName") userName: String,
    ): Call<LoadedProfileEntity>

    @GET("/users/{userName}/repos")
    fun loadUsersRepositories(
        @Path("userName") userName: String,
    ): Call<List<GitHubRepoEntity>>
}
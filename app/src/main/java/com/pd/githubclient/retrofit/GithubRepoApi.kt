package com.pd.githubclient.retrofit

import com.pd.githubclient.domain.entities.GithubRepoEntity
import com.pd.githubclient.domain.entities.ProfileEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubRepoApi {

    @GET("/users/{userName}")
    fun loadUserByName(
        @Path("userName") userName: String,
    ): Call<ProfileEntity>

    @GET("/users/{userName}/repos")
    fun loadReposByUser(
        @Path("userName") userName: String,
    ): Call<List<GithubRepoEntity>>
}
package com.pd.githubclient.retrofit

import com.pd.githubclient.domain.GitHubRepoEntity
import com.pd.githubclient.data.ProfileEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import io.reactivex.rxjava3.core.Observable

interface GithubRepoApi {

    @GET("/users/{userName}")
    fun loadUserByName(
        @Path("userName") userName: String,
    ): Observable<ProfileEntity> // создаем потоки данных

    @GET("/users/{userName}/repos")
    fun loadReposByUser(
        @Path("userName") userName: String,
    ): Observable<List<GitHubRepoEntity>>
}
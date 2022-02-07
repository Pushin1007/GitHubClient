package com.pd.githubclient.domain.retrofit

import com.pd.githubclient.data.ProfileEntity
import com.pd.githubclient.domain.GitHubRepoEntity
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {

    @GET("/users/{userName}")
    fun loadUserByName(
        @Path("userName") userName: String,
    ): Observable<ProfileEntity> // создаем потоки данных

    @GET("/users/{userName}/repos")
    fun loadUsersRepositories(
        @Path("userName") userName: String,
    ): Observable<List<GitHubRepoEntity>>
}
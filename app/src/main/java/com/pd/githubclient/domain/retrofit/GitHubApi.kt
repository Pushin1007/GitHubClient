package com.pd.githubclient.domain.retrofit

import com.pd.githubclient.data.ProfileEntity
import com.pd.githubclient.domain.GitHubRepoEntity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {

    @GET("/users/{userName}")
    fun loadUserByName(
        @Path("userName") userName: String,
    ): Single<ProfileEntity> // создаем потоки данных

    @GET("/users/{userName}/repos")
    fun loadUsersRepositories(
        @Path("userName") userName: String,
    ): Single<List<GitHubRepoEntity>>// переделываю на single, т.к. он более удобен для http запросов
}
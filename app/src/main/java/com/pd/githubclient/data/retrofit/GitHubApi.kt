package com.pd.githubclient.data.retrofit

import com.pd.githubclient.domain.ProfileEntity
import com.pd.githubclient.data.GitHubRepoEntity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {

    @GET("/users/{userName}")
    fun loadUserByName(
        @Path("userName") userName: String,
    ): Observable<ProfileEntity> // создаем потоки данных. Оставлю сдесь  Observable для разнообразия

    @GET("/users/{userName}/repos")
    fun loadUsersRepositories(
        @Path("userName") userName: String,
    ): Single<List<GitHubRepoEntity>>// переделываю на single, т.к. он более удобен для http запросов
}
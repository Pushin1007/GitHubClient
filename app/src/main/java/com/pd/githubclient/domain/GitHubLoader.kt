package com.pd.githubclient.domain

import com.pd.githubclient.BASE_URL
import com.pd.githubclient.data.ProfileEntity
import com.pd.githubclient.domain.retrofit.GitHubApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class GitHubLoader {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())//TODO
        .build()

    private var api: GitHubApi = retrofit.create(GitHubApi::class.java)

    // реализуем интерфейс ретрофита - создаем потоки данных
    fun loadUserEntityAsync(
        userName: String
    ): Single<ProfileEntity> {// меняем коллбек на Observable-источник данных
        return api.loadUserByName(userName)
    }

    fun loadUserRepositoriesAsync(
        userName: String
    ): Single<List<GitHubRepoEntity>> {
        return api.loadUsersRepositories(userName)
    }
}
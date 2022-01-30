package com.pd.githubclient.domain

import com.pd.githubclient.BASE_URL
import com.pd.githubclient.data.ProfileEntity
import com.pd.githubclient.domain.retrofit.GitHubApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GitHubLoader {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var api: GitHubApi = retrofit.create(GitHubApi::class.java)


    fun loadUserEntityAsync(
        userName: String,
        callback: (gitUserEntity: ProfileEntity?) -> Unit
    ) {
        api.loadUserByName(userName).enqueue(object : Callback<ProfileEntity> {
            override fun onResponse(
                call: Call<ProfileEntity>,
                response: Response<ProfileEntity>
            ) {
                callback.invoke(response.body()!!)
            }

            override fun onFailure(
                call: Call<ProfileEntity>,
                t: Throwable
            ) {
                callback.invoke(null)
            }

        })
    }

    fun loadUserRepositoriesAsync(
        userName: String,
        callback: (gitHubRepoEntity: List<GitHubRepoEntity>?) -> Unit
    ) {
        api.loadUsersRepositories(userName).enqueue(object : Callback<List<GitHubRepoEntity>> {
            override fun onResponse(
                call: Call<List<GitHubRepoEntity>>,
                response: Response<List<GitHubRepoEntity>>
            ) {
                callback.invoke(response.body())
            }

            override fun onFailure(
                call: Call<List<GitHubRepoEntity>>,
                t: Throwable
            ) {
                callback.invoke(null)
            }

        }
        )
    }
}
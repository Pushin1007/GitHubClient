package com.pd.githubclient.domainMy.entities

import com.google.gson.annotations.SerializedName

data class GithubRepoEntity(
    @SerializedName("id")
    val id: Long,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("updated_at")
    val updatedAt: String
)
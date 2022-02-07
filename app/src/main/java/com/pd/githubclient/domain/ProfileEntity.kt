package com.pd.githubclient.domain

import com.google.gson.annotations.SerializedName

// загружаемые поля пользователя
data class ProfileEntity(
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    @SerializedName("login")
    val login: String
)


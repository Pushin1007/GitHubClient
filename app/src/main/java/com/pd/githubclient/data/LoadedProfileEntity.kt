package com.pd.githubclient.data

data class LoadedProfileEntity(
    val avatar_url: String?,
    val bio: String?,
    val blog: String?,
    val created_at: String?,
    val email: String?,
    val events_url: String,
    val gists_url: String,
    val gravatar_id: String,
    val id: Int,
    val login: String,
    val name: String?,
    val public_gists: Int,
    val public_repos: Int,
    val received_events_url: String,
    val repos_url: String,
    val starred_url: String,
    val subscriptions_url: String,
    val twitter_username: Any,
    val type: String,
    val updated_at: String,
    val url: String
)
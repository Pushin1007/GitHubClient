package com.pd.githubclient.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val userName: String
) : Parcelable {
    companion object {
        fun getUsers() = listOf(
            User("Dmitry"),
            User("Dmitry"),
            User("Andrey"),

            )

    }
}
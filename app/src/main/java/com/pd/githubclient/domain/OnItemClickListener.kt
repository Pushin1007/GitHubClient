package com.pd.githubclient.domain

import com.pd.githubclient.data.GitHubUser

interface OnItemClickListener {

    fun onItemClick(user: GitHubUser)

}
package com.pd.githubclient.data

class GitNamesRepo {

    private val list = mutableListOf<GitHubUser>(
        GitHubUser("Pushin1007"),
        GitHubUser("kshalnov"),
        GitHubUser("mentatusn")
    )


    fun addUserName(user: GitHubUser) {
        list.add(user)
    }

    fun addListOfNames(names: List<GitHubUser>) {
        list.addAll(names)
    }

    fun getList(): List<GitHubUser> {
        val copy = mutableListOf<GitHubUser>()
        copy.addAll(list)
        return copy
    }

}
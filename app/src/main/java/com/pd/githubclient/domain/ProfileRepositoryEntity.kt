package com.pd.githubclient.domain

class ProfileRepositoryEntity {

    val loadedEntityCache = mutableListOf<ProfileEntity>()


    fun getProfileByLogin(login : String): ProfileEntity?{
        loadedEntityCache.forEach { profile->
            if(profile.login==login){
                return profile
            }
        }
        return null
    }
}
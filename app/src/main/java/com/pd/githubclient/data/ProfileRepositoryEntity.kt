package com.pd.githubclient.data

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
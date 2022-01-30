package com.pd.githubclient.data

class LoadedProfileRepository {

    val loadedEntityCache = mutableListOf<LoadedProfileEntity>()


    fun getProfileByLogin(login : String): LoadedProfileEntity?{
        loadedEntityCache.forEach { profile->
            if(profile.login==login){
                return profile
            }
        }
        return null
    }
}
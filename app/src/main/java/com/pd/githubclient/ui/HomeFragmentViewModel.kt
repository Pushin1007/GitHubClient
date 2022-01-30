package com.pd.githubclient.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pd.githubclient.data.GitHubUser
import com.pd.githubclient.data.GitNamesRepo
import com.pd.githubclient.data.LoadedProfileRepository
import com.pd.githubclient.domain.*
import java.io.IOException

class HomeFragmentViewModel(
    private val loader: GitHubLoader,
    private val repository: GitNamesRepo,
    private val cacheRepository: LoadedProfileRepository
) : ViewModel() {

    private val _createdAdapterLiveData = MutableLiveData<ProfilesRecyclerViewAdapter>()
    val createdAdapterLiveData: LiveData<ProfilesRecyclerViewAdapter> = _createdAdapterLiveData

    private val _dataLoadedLiveData = MutableLiveData<Event<DataSearchResponse>>()
    val dataLoadedLiveDataSearch: LiveData<Event<DataSearchResponse>> = _dataLoadedLiveData

    fun getUserNames(adapterProfiles: ProfilesRecyclerViewAdapter) {
        adapterProfiles.setItems(repository.getList())
        adapterProfiles.setListener(implementListener())
        _createdAdapterLiveData.value = adapterProfiles

    }

    private fun implementListener(): OnItemClickListener {
        return object : OnItemClickListener {
            override fun onItemClick(user: GitHubUser) {
                try {

                    loader.loadUserEntityAsync(user.name) { profile ->
                        if (profile != null) {
                            cacheRepository.loadedEntityCache.add(profile)
                            _dataLoadedLiveData.postValue(Event(DataSearchResponse(user.name, true)))
                        } else {
                            _dataLoadedLiveData.postValue(Event(DataSearchResponse(user.name, false)))
                        }
                    }
                } catch (e: IOException){

                }
            }
        }
    }
}
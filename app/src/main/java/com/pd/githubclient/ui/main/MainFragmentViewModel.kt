package com.pd.githubclient.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pd.githubclient.data.User

import com.pd.githubclient.data.ProfileRepositoryEntity
import com.pd.githubclient.domain.*
import com.pd.githubclient.domain.adapters.MainRecyclerViewAdapter
import com.pd.githubclient.domain.repository.Repository
import java.io.IOException

class MainFragmentViewModel(
    private val loader: GitHubLoader,
    private val repository: Repository,
    private val cacheRepository: ProfileRepositoryEntity
) : ViewModel() {

    // MutableLiveData в отличии от LiveData позволяет себя изменить и пушить в нее данные
    private val _createdAdapterLiveData = MutableLiveData<MainRecyclerViewAdapter>()
    val createdAdapterLiveData: LiveData<MainRecyclerViewAdapter> = _createdAdapterLiveData

    private val _dataLoadedLiveData = MutableLiveData<Event<DataSearchResponse>>()
    val dataLoadedLiveDataSearch: LiveData<Event<DataSearchResponse>> = _dataLoadedLiveData

    fun getUserNames(adapterProfiles: MainRecyclerViewAdapter) {
        adapterProfiles.setItems(repository.getUserFromLocalStorage())
        adapterProfiles.setListener(implementListener())
        _createdAdapterLiveData.value = adapterProfiles
    }

    private fun implementListener(): OnItemClickListener {
        return object : OnItemClickListener {
            override fun onItemClick(user: User) {
                try {

                    loader.loadUserEntityAsync(user.userName) { profile ->
                        if (profile != null) {
                            cacheRepository.loadedEntityCache.add(profile)
                            _dataLoadedLiveData.postValue(
                                Event(
                                    DataSearchResponse(
                                        user.userName,
                                        true
                                    )
                                )
                            )
                        } else {
                            _dataLoadedLiveData.postValue(
                                Event(
                                    DataSearchResponse(
                                        user.userName,
                                        false
                                    )
                                )
                            )
                        }
                    }
                } catch (e: IOException) {

                }
            }
        }
    }
}
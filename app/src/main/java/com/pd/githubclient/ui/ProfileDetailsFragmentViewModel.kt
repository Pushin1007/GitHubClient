package com.pd.githubclient.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pd.githubclient.data.LoadedProfileRepository
import com.pd.githubclient.domain.GitHubLoader
import com.pd.githubclient.domain.GitHubRepoEntity
import java.io.IOException

class ProfileDetailsFragmentViewModel(
    private val cacheRepository: LoadedProfileRepository,
    private val loader: GitHubLoader
): ViewModel() {

    private val _dataLoadedLiveData = MutableLiveData<DataDetailResponse>()
    val dataLoadedLiveDataSearch: LiveData<DataDetailResponse> = _dataLoadedLiveData

    fun getData(login: String) {
        var repositoriesList = emptyList<GitHubRepoEntity>()
        try {
            loader.loadUserRepositoriesAsync(login) { list ->
                if (list != null) {
                    repositoriesList = list
                    _dataLoadedLiveData.postValue(
                        DataDetailResponse(
                            repositoriesList, cacheRepository.getProfileByLogin(login)!!, true
                        )
                    )
                } else {
                    _dataLoadedLiveData.postValue(
                        DataDetailResponse(
                            repositoriesList, cacheRepository.getProfileByLogin(login)!!, false
                        )
                    )
                }
            }
        } catch (e: IOException){

        }
    }
}
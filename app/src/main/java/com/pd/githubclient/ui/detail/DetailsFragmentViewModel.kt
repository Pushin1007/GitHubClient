package com.pd.githubclient.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pd.githubclient.AppState
import com.pd.githubclient.data.ProfileRepositoryEntity
import com.pd.githubclient.domain.GitHubLoader
import com.pd.githubclient.domain.GitHubRepoEntity
import com.pd.githubclient.ui.DataDetailResponse
import java.io.IOException

class DetailsFragmentViewModel(
    private val cacheRepository: ProfileRepositoryEntity,
    private val loader: GitHubLoader
) : ViewModel() {
    //    val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private val _dataLoadedLiveData = MutableLiveData<DataDetailResponse>()
    val dataLoadedLiveDataSearch: LiveData<DataDetailResponse> = _dataLoadedLiveData

    fun getData(login: String) {
        //      liveDataToObserve.value=AppState.Loading
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
        } catch (e: IOException) {

        }
    }
}
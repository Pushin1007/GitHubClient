package com.pd.githubclient.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pd.githubclient.AppState
import com.pd.githubclient.data.ProfileRepositoryEntity
import com.pd.githubclient.domain.Event
import com.pd.githubclient.domain.GitHubLoader
import com.pd.githubclient.domain.GitHubRepoEntity
import com.pd.githubclient.ui.DataDetailResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.IOException

class DetailsFragmentViewModel(
    private val cacheRepository: ProfileRepositoryEntity,
    private val loader: GitHubLoader
) : ViewModel() {
    //    val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private val _dataLoadedLiveData = MutableLiveData<DataDetailResponse>()
    val dataLoadedLiveDataSearch: LiveData<DataDetailResponse> = _dataLoadedLiveData

    private val _onErrorLiveData = MutableLiveData<Event<Unit>>()
    val onErrorLiveData: LiveData<Event<Unit>> = _onErrorLiveData


    fun getData(login: String) {
        loader.loadUserRepositoriesAsync(login)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = {
                    _onErrorLiveData.postValue(Event(Unit))
                },
                onNext = { list ->
                    _dataLoadedLiveData.postValue(
                        DataDetailResponse(
                            list,
                            cacheRepository.getProfileByLogin(login)
                        )
                    )
                }
            )
    }
}
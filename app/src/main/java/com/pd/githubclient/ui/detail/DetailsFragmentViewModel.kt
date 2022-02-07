package com.pd.githubclient.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pd.githubclient.domain.ProfileRepositoryEntity
import com.pd.githubclient.data.Event
import com.pd.githubclient.data.GitHubLoader
import com.pd.githubclient.ui.DataDetailResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class DetailsFragmentViewModel(
    private val cacheRepository: ProfileRepositoryEntity,
    private val loader: GitHubLoader
) : ViewModel() {

    private val _dataLoadedLiveData = MutableLiveData<DataDetailResponse>()
    val dataLoadedLiveDataSearch: LiveData<DataDetailResponse> = _dataLoadedLiveData

    private val _onErrorLiveData = MutableLiveData<Event<Unit>>()


    fun getData(login: String) {
        loader.loadUserRepositoriesAsync(login)
            .retry(3)//в случае ошибки перезапустит поток
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = {
                    _onErrorLiveData.postValue(Event(Unit)) // ничего не передаем. заглушка
                },
                onSuccess  = { list ->// метода onNext в Single нет, данные получаются когда полностью закончится процесс
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
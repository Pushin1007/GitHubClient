package com.pd.githubclient.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pd.githubclient.data.User

import com.pd.githubclient.data.ProfileRepositoryEntity
import com.pd.githubclient.domain.*
import com.pd.githubclient.domain.adapters.MainRecyclerViewAdapter
import com.pd.githubclient.domain.repository.Repository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.IOException

class MainFragmentViewModel(
    private val loader: GitHubLoader,
    private val repository: Repository,
    private val cacheRepository: ProfileRepositoryEntity
) : ViewModel() {

    // MutableLiveData в отличии от LiveData позволяет себя изменить и пушить в нее данные
    private val _createdAdapterLiveData = MutableLiveData<MainRecyclerViewAdapter>()
    val createdAdapterLiveData: LiveData<MainRecyclerViewAdapter> = _createdAdapterLiveData

    private val _dataLoadedLiveData = MutableLiveData<Event<String>>()
    val dataLoadedLiveDataSearch: LiveData<Event<String>> = _dataLoadedLiveData

    private val _onErrorLiveData = MutableLiveData<Event<Unit>>()
    val onErrorLiveData: LiveData<Event<Unit>> = _onErrorLiveData

    fun getUserNames(adapterProfiles: MainRecyclerViewAdapter) {
        adapterProfiles.setItems(repository.getUserFromLocalStorage())
        adapterProfiles.setListener(implementListener())
        _createdAdapterLiveData.value = adapterProfiles
    }

    private fun implementListener(): OnItemClickListener {
        return object : OnItemClickListener {
            override fun onItemClick(user: User) {

                loader.loadUserEntityAsync(user.userName)// ловим данные
                    .subscribeOn(Schedulers.io())// создаем "распсание"
                    .observeOn(AndroidSchedulers.mainThread())// переключаемся на главный поток
                    .subscribeBy(
                        onError = {
                            _onErrorLiveData.postValue(Event(Unit))
                        },
                        onNext = { profile ->
                            cacheRepository.loadedEntityCache.add(profile)
                            _dataLoadedLiveData.postValue(Event(profile.login))
                        })
            }
        }
    }
}
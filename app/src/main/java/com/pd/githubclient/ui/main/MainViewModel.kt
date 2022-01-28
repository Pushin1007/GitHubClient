package com.pd.githubclient.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pd.githubclient.AppState
import com.pd.githubclient.domain.repository.Repository
import com.pd.githubclient.domain.repository.RepositoryImpl

class MainViewModel(private val repository: Repository = RepositoryImpl()) : ViewModel() {
    // MutableLiveData в отличии от LiveData позволяет себя изменить и пушить в нее данные
    private val liveData = MutableLiveData<AppState>()

    fun getLiveData(): LiveData<AppState> = liveData //переопределяем геттер для liveData


    fun getUserFromLocalSource() {// получение юзера
        liveData.value = AppState.Loading
        // после завершения загрузки и получения колбека от API , изменяем значение в liveData
        liveData.value = AppState.Success(repository.getUserFromLocalStorage())
    }
}
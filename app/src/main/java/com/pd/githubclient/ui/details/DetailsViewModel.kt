package com.pd.githubclient.ui.details

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pd.githubclient.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel(private val repository: Repository) : ViewModel(), LifecycleObserver,
    WeatherLoader.WeatherLoaderErrorListener {
    val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun loadData(city: City) {
        liveDataToObserve.value = AppState.Loading

        viewModelScope.launch(Dispatchers.IO) {// переделываем обычный поток на корутины
            // viewModelScope интегрирован в ViewModel и его не нужно отменять
            val data = repository.getWeatherFromServer(city.lat, city.lon, this@DetailsViewModel)
            data?.city = city
            data?.let { repository.saveEntity(it) }
            // получаем наши данные
            // синхронизируемся с потоком UI  и сетим AppState.Success с нашими данными которые получены от сервера
            if (data != null) {

                liveDataToObserve.postValue(AppState.Success(listOf(data)))
            }
        }
    }

    override fun showError(throwable: Throwable) { // реализация метода  загрузки ошибки в  liveDataToObserve
        liveDataToObserve.postValue(AppState.Error(throwable))
    }
}
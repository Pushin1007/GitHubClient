package com.pd.githubclient.data

import com.pd.githubclient.BASE_URL
import com.pd.githubclient.domain.ProfileEntity
import com.pd.githubclient.data.retrofit.GitHubApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GitHubLoader {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())//адаптер который преобразовывает данные в Observable
        .build()

    private var api: GitHubApi = retrofit.create(GitHubApi::class.java)

    // реализуем интерфейс ретрофита - создаем потоки данных
    fun loadUserEntityAsync(
        userName: String
    ): Observable<ProfileEntity> {// меняем коллбек на Observable-источник данных
        //делаем из холодного горячий источникданных.
        return api.loadUserByName(userName)
            .subscribeOn(Schedulers.computation())// запускаем в пуле потоков не computation
            .replay(1) // возвращает  последние данные всего 1шт , аналог LiveData
            .refCount()// Начнет работу как только появится первый подписчик
    }


    fun loadUserRepositoriesAsync(
        userName: String
    ): Single<List<GitHubRepoEntity>> {
        return api.loadUsersRepositories(userName)
            .subscribeOn(Schedulers.computation())
            .cache()//кешируем все данные и воспроизводим их для каждой подписки
    }
}
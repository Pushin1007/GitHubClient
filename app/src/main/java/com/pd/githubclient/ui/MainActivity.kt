package com.pd.githubclient.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pd.githubclient.R
import com.pd.githubclient.databinding.ActivityMainBinding
import com.pd.githubclient.domain.RepositoryEntity

/*
Сквозное приложение этого курса - клиент ГитХаб с отображением списка пользователей, профиля, репозиториев.
 Чем больше всего, тем лучше.
Начните разработку приложения - структуру пакетов, репозитории,
 базовые презентеры/вью, подключите вью биндинг, правильно настройте gitignore и так далее.
  Чем больше сделаете сейчас, тем проще будет потом.
 */
class MainActivity : AppCompatActivity(), MainView {

    private var binding: ActivityMainBinding? = null
    val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }


    override fun showProgress() {
        TODO("Not yet implemented")
    }

    override fun showResult(repositories: RepositoryEntity) {
        TODO("Not yet implemented")
    }

    override fun showError(throwable: Throwable) {
        TODO("Not yet implemented")
    }


}
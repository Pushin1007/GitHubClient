package com.pd.githubclient.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pd.githubclient.R
import com.pd.githubclient.databinding.ActivityMainBinding

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

    override fun showResult() {
        TODO("Not yet implemented")
    }


}
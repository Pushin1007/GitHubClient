package com.pd.githubclient.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pd.githubclient.R
import com.pd.githubclient.databinding.ActivityMainBinding
import com.pd.githubclient.ui.main.MainFragment

/*
ДЗ#1
Сквозное приложение этого курса - клиент ГитХаб с отображением списка пользователей, профиля, репозиториев.
 Чем больше всего, тем лучше.
Начните разработку приложения - структуру пакетов, репозитории,
 базовые презентеры/вью, подключите вью биндинг, правильно настройте gitignore и так далее.
  Чем больше сделаете сейчас, тем проще будет потом.
 */
/*
ДЗ#2
-Сделайте экран со списком логинов пользователей гитхаб (фейковые данные в репо)
-По нажатию на логин открывайте экран конкретного пользователя
-На экране отображайте аватар, имя профиля, список репозиториев
-Для репозиториев, пользователей и прочих сделайте Repo/DataSource сущности с интерфейсами и реализациями
-Храните репо в App классе
+Используйте viewBinding в проекте
-Используйте что-то из MVP/MVVM/MVI в своём проекте
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment.newInstance()).commit()
    }

}

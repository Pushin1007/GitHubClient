package com.pd.githubclient.uiMY.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pd.githubclient.AppState
import com.pd.githubclient.R
import com.pd.githubclient.databinding.MainFragmentBinding
import com.pd.githubclient.domainMy.entities.User
import com.pd.githubclient.uiMY.adapters.MainFragmentAdapter

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }


    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private var adapter: MainFragmentAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            mainFragmentRecyclerView.adapter = adapter //
            viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
            viewModel.getUserFromLocalSource()
        }
    }


    private fun renderData(appState: AppState) = with(binding) {
        //этот метод будет вызываться когда мы будем получать какой-то ивент от наших подписок


        when (appState) {
            is AppState.Success -> { //если загрузка прошла ОК

                //здесь будем реагировать на нажатия
                adapter = MainFragmentAdapter(object : OnItemViewClickListener { //создаем адаптер
                    override fun onItemViewClick(user: User) { //передаем ему реакцию от слушателя на один из жлементов списка
                        val manager = activity?.supportFragmentManager
//                        manager?.let { manager ->
//                            val bundle = Bundle().apply {
//                                putParcelable(BUNDLE_EXTRA, user)
//
//                            }
//                            manager.beginTransaction()
//                                .add(R.id.container, DetailsFragment.newInstance(bundle))
//                                .addToBackStack("")
//                                .commitAllowingStateLoss()
//                        }
                    }
                }).apply {
                    setUser(appState.userData)
                }
                loadingUserLayout.isVisible = false//скрываем виджет загрузки
                mainFragmentRecyclerView.adapter = adapter
                mainFragmentRecyclerView.isVisible = true // показываем список
            }
            is AppState.Loading -> { //если идет загрузка то просто  показываем виджет загрузки
                mainFragmentRecyclerView.isVisible = false
                loadingUserLayout.isVisible = true

            }
            is AppState.Error -> {
                mainFragmentRecyclerView.isVisible = false
                loadingUserLayout.isVisible = false
                Toast.makeText(context, R.string.errorLoadUser, Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // обнуляем ссылку для на фрагмент

    }

    interface OnItemViewClickListener { //создаем интерфейс для того чтобы отлеживание на нажатие можно было пробросить дальше в адаптер
        fun onItemViewClick(user: User)
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
/*
private fun renderData(appState: AppState) = with(binding) {

    when (appState) {
        is AppState.Success -> {
            mainFragmentLoadingLayout.visibility = View.GONE
            adapter = MainFragmentAdapter(object : OnItemViewClickListener {
                override fun onItemViewClick(weather: Weather) {
                    val manager = activity?.supportFragmentManager
                    manager?.let { manager ->
                        /*
                        .let - функция расширения которая возвращает  результат переданной функции, передав в нее объект на котором она вызвана
                        В частности здесь  функция возвращает объект уже проверенный на nullable.
                        и в последствии с ним уже можно будет работатть как с не  nullable объектом
                         */
                        val bundle = Bundle().apply {
                            /*
                            .apply - функция расширения которая позволяет на уже созданном объекте вызвать его методы без ссылки на сам объект
                            Позволяет соеденить создание объекта с его инициализацией
                             */
                            putParcelable(BUNDLE_EXTRA, weather)
                        }
                        manager.beginTransaction()
                            .add(R.id.container, DetailsFragment.newInstance(bundle))
                            .addToBackStack("")
                            .commitAllowingStateLoss()
                    }
                }
            }).apply {
                setWeather(appState.weatherData)
            }
            mainFragmentRecyclerView.adapter = adapter
        }
        is AppState.Loading -> {
            mainFragmentLoadingLayout.visibility = View.VISIBLE
        }
        is AppState.Error -> {
            mainFragmentLoadingLayout.visibility = View.GONE

            mainImageButton.showSnackBar(
                getString(R.string.error),
                getString(R.string.reload)
            ) {
                //функцию на вход в данном случае мы можем вынести за скобки
                viewModel.getWeatherFromLocalSourceRus()
            }
        }
    }
}
*/
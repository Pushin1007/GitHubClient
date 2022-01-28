package com.pd.githubclient.ui.main

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
import com.pd.githubclient.domain.entities.User
import com.pd.githubclient.ui.adapters.MainFragmentAdapter

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
        loadingUserLayout.isVisible = false
        mainFragmentRecyclerView.isVisible = false
        when (appState) {
            is AppState.Success -> {

                //здесь будем реагировать на нажатия
                adapter = MainFragmentAdapter(object : OnItemViewClickListener {
                    override fun onItemViewClick(user: User) {
                        Toast.makeText(context, "hf,jnftn", Toast.LENGTH_SHORT).show()
                    }
                }).apply {
                    setUser(appState.userData)
                }
                mainFragmentRecyclerView.adapter = adapter
                mainFragmentRecyclerView.isVisible = true // показываем список
            }
            is AppState.Loading -> {
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
        _binding = null

    }

    interface OnItemViewClickListener {
        fun onItemViewClick(user: User)
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
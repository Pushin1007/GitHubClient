package com.pd.githubclient.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pd.githubclient.AppState
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
        when (appState) {
            is AppState.Success -> {

                //здесь будем реагировать на нажатия
                adapter = MainFragmentAdapter(object : OnItemViewClickListener {
                    override fun onItemViewClick(user: User) {

                    }
                }).apply {
                    setUser(appState.userData)
                }
                mainFragmentRecyclerView.adapter = adapter


            }
            is AppState.Loading -> {
            }
            is AppState.Error -> {
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
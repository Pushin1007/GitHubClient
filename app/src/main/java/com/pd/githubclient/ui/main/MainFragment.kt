package com.pd.githubclient.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.pd.githubclient.R
import com.pd.githubclient.databinding.MainFragmentBinding
import com.pd.githubclient.data.adapters.MainRecyclerViewAdapter
import com.pd.githubclient.ui.MainActivity
import com.pd.githubclient.ui.detail.DetailsFragment

import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<MainFragmentViewModel>()
    private val adapter = MainRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getUserNames(adapter)

        viewModel.createdAdapterLiveData.observe(viewLifecycleOwner) { adapter ->
            binding.recyclerView.adapter = adapter
        }

        viewModel.dataLoadedLiveDataSearch.observe(viewLifecycleOwner) { responseEvent ->

            responseEvent.getContentIfNotHandled()?.let { responseEvent -> //если загрузка прошла ОК
                parentFragmentManager.beginTransaction().replace(
                    (requireActivity() as MainActivity).binding.container.id,
                    DetailsFragment.getNewInstance(responseEvent)
                ).addToBackStack(null).commit()
            }
            super.onViewCreated(view, savedInstanceState)
        }
        viewModel.onErrorLiveData.observe(viewLifecycleOwner)// при наступлении ошибки
        { errorEvent ->
            errorEvent.getContentIfNotHandled()?.let {
                Toast.makeText(requireContext(), R.string.errorLoadUser, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // обнуляем ссылку для на фрагмент

    }


    companion object {
        fun newInstance() = MainFragment()
    }

}

package com.pd.githubclient.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.pd.githubclient.databinding.MainFragmentBinding
import com.pd.githubclient.domain.DataSearchResponse
import com.pd.githubclient.domain.adapters.MainRecyclerViewAdapter
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
            var searchResponse: DataSearchResponse? = null
            responseEvent.getContentIfNotHandled()?.let { responseEvent ->
                searchResponse = responseEvent
            }
            if (searchResponse != null) { //если загрузка прошла ОК
                if (searchResponse!!.isSuccess) {
                    Log.d("@@@@", "onViewCreated: suc")
                    parentFragmentManager.beginTransaction().replace(
                        (requireActivity() as MainActivity).binding.container.id,
                        DetailsFragment.getNewInstance(searchResponse!!.login)
                    ).addToBackStack(null).commit()
                } else if (!searchResponse!!.isSuccess) {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // обнуляем ссылку для на фрагмент

    }


    companion object {
        fun newInstance() = MainFragment()
    }
}

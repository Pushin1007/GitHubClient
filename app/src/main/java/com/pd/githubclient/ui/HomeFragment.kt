package com.pd.githubclient.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.pd.githubclient.databinding.HomeFragmentLayoutBinding
import com.pd.githubclient.domain.DataSearchResponse
import com.pd.githubclient.domain.ProfilesRecyclerViewAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentLayoutBinding
    private val viewModel by viewModel<HomeFragmentViewModel>()
    private val adapter = ProfilesRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentLayoutBinding.inflate(inflater)
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
            if (searchResponse != null) {
                if (searchResponse!!.isSuccess) {
                    Log.d("@@@@", "onViewCreated: suc")
                    parentFragmentManager.beginTransaction().replace(
                        (requireActivity() as MainActivity).binding.container.id,
                        ProfileDetailsFragment.getNewInstance(searchResponse!!.login)
                    ).addToBackStack(null).commit()
                } else if (!searchResponse!!.isSuccess) {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }
}

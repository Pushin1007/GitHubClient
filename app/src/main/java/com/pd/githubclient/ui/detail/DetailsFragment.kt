package com.pd.githubclient.ui.detail

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.pd.githubclient.R
import com.pd.githubclient.databinding.DetailFragmentBinding
import com.pd.githubclient.domain.DataSearchResponse
import com.pd.githubclient.domain.adapters.DetailsRecyclerViewAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.NullPointerException

private const val argsKey = "LOGIN"

class DetailsFragment : Fragment() {

    private var login: String? = null
    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<DetailsFragmentViewModel>()
    private val adapter = DetailsRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = DetailFragmentBinding.inflate(inflater)
        return binding.root
    }


    override fun onAttach(context: Context) {
        login = requireArguments().getString(argsKey)
        if (login == null) {
            throw NullPointerException("Login should be provided")
        }
        super.onAttach(context)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.repositoriesRecyclerView.adapter = adapter
        binding.loginTextView.text = login
        viewModel.getData(login!!)

        viewModel.dataLoadedLiveDataSearch.observe(viewLifecycleOwner) { response ->
                Glide.with(this).load(response.profile!!.avatarUrl).into(binding.profileImageView)
                adapter.setItems(response.repositories)


        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun getNewInstance(login: String): Fragment {
            val bundle = Bundle()
            bundle.putString(argsKey, login)
            return DetailsFragment().apply { arguments = bundle }
        }
    }
}
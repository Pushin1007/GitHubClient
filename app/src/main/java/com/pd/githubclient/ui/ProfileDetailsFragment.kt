package com.pd.githubclient.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.pd.githubclient.databinding.ProfileDetailLayoutBinding
import com.pd.githubclient.domain.RepositoriesRecyclerViewAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.NullPointerException

private const val argsKey= "LOGIN"

class ProfileDetailsFragment: Fragment() {

    private var login: String? = null


    private val viewModel by viewModel<ProfileDetailsFragmentViewModel>()

    private lateinit var binding: ProfileDetailLayoutBinding

    private val adapter= RepositoriesRecyclerViewAdapter()

    override fun onAttach(context: Context) {
        login = requireArguments().getString(argsKey)
        if (login == null) {
            throw NullPointerException("Login should be provided")
        }
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProfileDetailLayoutBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.repositoriesRecyclerView.adapter = adapter

        viewModel.getData(login!!)

        viewModel.dataLoadedLiveDataSearch.observe(viewLifecycleOwner){ response->
            if (response.isSuccess){
                Log.d("@@@@", response.repositories.size.toString() + " aaaa")
                Glide.with(this).load(response.profile.avatar_url).into(binding.profileImageView)
                adapter.setItems(response.repositories)
            } else{
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }

        }

        super.onViewCreated(view, savedInstanceState)
    }


    companion object{
        fun getNewInstance(login: String): Fragment {
            val bundle= Bundle()
            bundle.putString(argsKey, login)
            return ProfileDetailsFragment().apply { arguments = bundle }
        }
    }
}
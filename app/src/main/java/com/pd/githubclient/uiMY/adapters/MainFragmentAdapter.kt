package com.pd.githubclient.uiMY.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pd.githubclient.databinding.FragmentMainRecyclerItemBinding
import com.pd.githubclient.domainMy.entities.User
import com.pd.githubclient.uiMY.main.MainFragment

class MainFragmentAdapter(private val itemClickListener: MainFragment.OnItemViewClickListener) // получаем на вход слушателя который приходит от MainFragment
    : RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>() {
    private var userData: List<User> = listOf()
    private lateinit var binding: FragmentMainRecyclerItemBinding // объявляем  binding

    @SuppressLint("NotifyDataSetChanged") // когда придет список юзеров, будем перерисовывать весь список
    fun setUser(data: List<User>) {
        userData = data
        notifyDataSetChanged()
    }

    // переопередяем стандартные методы  RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        binding = FragmentMainRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MainViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(userData[position])
    }

    override fun getItemCount() = userData.size

    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //  ViewHolder
        fun bind(user: User) = with(binding) {
            mainFragmentRecyclerItemTextView.text =
                user.userName //сетим текст во вьюху из  user
            root.setOnClickListener { itemClickListener.onItemViewClick(user) } // принажатии на корневую вьюху мы передадим погоду
        }
    }
}
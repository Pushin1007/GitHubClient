package com.pd.githubclient.domain.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pd.githubclient.databinding.RecyclerViewItemBinding
import com.pd.githubclient.domain.GitHubRepoEntity
import com.pd.githubclient.ui.ItemViewHolder

class DetailsRecyclerViewAdapter: RecyclerView.Adapter<ItemViewHolder>() {

    private var itemList = emptyList<GitHubRepoEntity>()
    // переопередяем стандартные методы  RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val viewHolderBinding: RecyclerViewItemBinding =// объявляем  binding
            RecyclerViewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)

        return ItemViewHolder(viewHolderBinding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.nameTextView.text= item.name
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
    @SuppressLint("NotifyDataSetChanged") // когда придет список юзеров, будем перерисовывать весь список
    fun setItems(repositories: List<GitHubRepoEntity>){
        this.itemList= repositories
        notifyDataSetChanged()
    }


    private fun getItem(position: Int): GitHubRepoEntity {
        return itemList[position]
    }
}
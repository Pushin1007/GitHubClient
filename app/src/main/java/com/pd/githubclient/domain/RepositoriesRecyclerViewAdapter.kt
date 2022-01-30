package com.pd.githubclient.domain

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pd.githubclient.databinding.RecyclerViewItemBinding
import com.pd.githubclient.ui.ItemViewHolder

class RepositoriesRecyclerViewAdapter: RecyclerView.Adapter<ItemViewHolder>() {

    private var itemList = emptyList<GitHubRepoEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val viewHolderBinding: RecyclerViewItemBinding =
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

    fun setItems(repositories: List<GitHubRepoEntity>){
        this.itemList= repositories
        notifyDataSetChanged()
    }


    private fun getItem(position: Int): GitHubRepoEntity{
        return itemList[position]
    }
}
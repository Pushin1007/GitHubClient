package com.pd.githubclient.domain

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pd.githubclient.data.GitHubUser
import com.pd.githubclient.databinding.RecyclerViewItemBinding
import com.pd.githubclient.ui.ItemViewHolder

class ProfilesRecyclerViewAdapter : RecyclerView.Adapter<ItemViewHolder>() {

    private var itemList = emptyList<GitHubUser>()

    private lateinit var listener: OnItemClickListener

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
        holder.nameTextView.text = item.name
        holder.itemView.setOnClickListener { listener.onItemClick(item) }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setItems(list: List<GitHubUser>) {
        itemList = list
        notifyDataSetChanged()
    }

    fun setListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    private fun getItem(position: Int): GitHubUser {
        return itemList[position]
    }
}
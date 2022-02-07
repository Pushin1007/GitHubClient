package com.pd.githubclient.data.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pd.githubclient.domain.User
import com.pd.githubclient.databinding.RecyclerViewItemBinding
import com.pd.githubclient.data.OnItemClickListener
import com.pd.githubclient.ui.ItemViewHolder

class MainRecyclerViewAdapter : RecyclerView.Adapter<ItemViewHolder>() {

    private var itemList = emptyList<User>()

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
        holder.nameTextView.text = item.userName
        holder.itemView.setOnClickListener { listener.onItemClick(item) }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(list: List<User>) {
        itemList = list
        notifyDataSetChanged()
    }

    fun setListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    private fun getItem(position: Int): User {
        return itemList[position]
    }
}
package com.tapaafandi.githubuserapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tapaafandi.githubuserapp.R
import com.tapaafandi.githubuserapp.databinding.ItemSearchUserBinding
import com.tapaafandi.githubuserapp.domain.model.UserSearch

class SearchUserAdapter : RecyclerView.Adapter<SearchUserAdapter.SearchUserViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null
    private val mData = ArrayList<UserSearch>()

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setData(items: List<UserSearch>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchUserViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_search_user, parent, false)
        return SearchUserViewHolder(mView)
    }

    override fun onBindViewHolder(holder: SearchUserViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    override fun getItemCount(): Int = mData.size

    inner class SearchUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemSearchUserBinding.bind(itemView)
        fun bind(userItems: UserSearch) {
            binding.tvUsername.text = userItems.username

            Glide.with(itemView.context)
                .load(userItems.avatarUrl)
                .into(binding.ivUserImage)

            itemView.setOnClickListener { onItemClickCallback?.onItemClicked(userItems) }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: UserSearch)
    }
}
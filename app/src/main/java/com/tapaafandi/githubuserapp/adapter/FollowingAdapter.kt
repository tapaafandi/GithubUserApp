package com.tapaafandi.githubuserapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tapaafandi.githubuserapp.R
import com.tapaafandi.githubuserapp.databinding.ItemSearchUserBinding
import com.tapaafandi.githubuserapp.domain.model.UserSearch

class FollowingAdapter : RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    private val mData = ArrayList<UserSearch>()

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setData(items: ArrayList<UserSearch>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_search_user, parent, false)
        return FollowingViewHolder(mView)
    }

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    override fun getItemCount(): Int = mData.size

    inner class FollowingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
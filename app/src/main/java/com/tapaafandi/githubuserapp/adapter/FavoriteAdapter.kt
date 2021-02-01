package com.tapaafandi.githubuserapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tapaafandi.githubuserapp.R
import com.tapaafandi.githubuserapp.databinding.ItemFavoriteUserBinding
import com.tapaafandi.githubuserapp.domain.model.UserDetail

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    private val mData = ArrayList<UserDetail>()

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setData(items: List<UserDetail>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_favorite_user, parent, false)
        return FavoriteViewHolder(mView)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    override fun getItemCount(): Int = mData.size

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemFavoriteUserBinding.bind(itemView)
        fun bind(userItems: UserDetail) {
            with(binding) {
                tvName.text = userItems.name
                tvUsername.text = userItems.username
                tvLocation.text = userItems.location
                Glide.with(itemView.context)
                    .load(userItems.avatarUrl)
                    .into(ivUserFavoriteImages)
            }

            itemView.setOnClickListener { onItemClickCallback?.onItemClicked(userItems) }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: UserDetail)
    }
}
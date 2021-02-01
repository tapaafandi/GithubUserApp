package com.tapaafandi.githubuserapp.presentation.ui.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tapaafandi.githubuserapp.R
import com.tapaafandi.githubuserapp.adapter.FavoriteAdapter
import com.tapaafandi.githubuserapp.databinding.FragmentFavoriteBinding
import com.tapaafandi.githubuserapp.domain.model.UserDetail
import com.tapaafandi.githubuserapp.presentation.ui.detail_user.DetailUserActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var favoriteAdapter: FavoriteAdapter

    private val viewModel: FavoriteViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavoriteBinding.bind(view)

        favoriteAdapter = FavoriteAdapter()
        observeFavoriteUserData()
        recyclerViewSetup()
    }

    private fun observeFavoriteUserData() {
        viewModel.favoriteUser.observe(viewLifecycleOwner, { userFavorite ->
            if (userFavorite != null) {
                favoriteAdapter.setData(userFavorite)
                showFavoriteMessage(false)
            } else {
                showFavoriteMessage(true)
            }
        })
    }

    private fun recyclerViewSetup() {
        binding.rvFavoriteUser.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = favoriteAdapter
        }

        favoriteAdapter.setOnItemClickCallback(object : FavoriteAdapter.OnItemClickCallback {
            override fun onItemClicked(data: UserDetail) {
                Intent(requireContext(), DetailUserActivity::class.java).apply {
                    putExtra(DetailUserActivity.EXTRA_DETAIL, data.username)
                    startActivity(this)
                }
            }
        })
    }

    private fun showFavoriteMessage(state: Boolean) {
        if (state) {
            binding.tvWelcome.visibility = View.VISIBLE
            binding.ivGithubLogo.visibility = View.VISIBLE
        } else {
            binding.tvWelcome.visibility = View.GONE
            binding.ivGithubLogo.visibility = View.GONE
        }
    }
}
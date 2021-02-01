package com.tapaafandi.githubuserapp.presentation.ui.detail_user

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tapaafandi.githubuserapp.R
import com.tapaafandi.githubuserapp.adapter.FollowersAdapter
import com.tapaafandi.githubuserapp.databinding.FragmentFollowersBinding
import com.tapaafandi.githubuserapp.domain.model.UserSearch
import com.tapaafandi.githubuserapp.util.Constants.Companion.ARG_USERNAME
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FollowersFragment : Fragment(R.layout.fragment_followers) {

    private lateinit var binding: FragmentFollowersBinding
    private lateinit var followersAdapter: FollowersAdapter

    private val viewModel: FollowersViewModel by viewModels()

    companion object {
        fun newInstance(username: String): FollowersFragment {
            val fragment = FollowersFragment()
            val bundle = Bundle()
            bundle.putString(ARG_USERNAME, username)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFollowersBinding.bind(view)

        followersAdapter = FollowersAdapter()

        lifecycleScope.launch {
            requestFollowersData()
            isLoading(true)
        }

        observeFollowersData()
        recyclerViewSetup()

    }

    private suspend fun requestFollowersData() {
        val username = arguments?.getString(ARG_USERNAME)
        if (username != null) {
            viewModel.getListFollowersGithubUser(username)
        }
    }

    private fun observeFollowersData() {
        viewModel.followersUser.observe(requireActivity(), { followersItems ->
            if (followersItems != null) {
                followersAdapter.setData(followersItems as ArrayList<UserSearch>)
                isLoading(false)
            }
        })
    }

    private fun recyclerViewSetup() {
        binding.apply {
            rvFollowersUser.layoutManager = LinearLayoutManager(requireContext())
            rvFollowersUser.setHasFixedSize(true)
            rvFollowersUser.adapter = followersAdapter
        }

        followersAdapter.setOnItemClickCallback(object : FollowersAdapter.OnItemClickCallback {
            override fun onItemClicked(data: UserSearch) {
                Intent(requireContext(), DetailUserActivity::class.java).apply {
                    putExtra(DetailUserActivity.EXTRA_DETAIL, data.username)
                    startActivity(this)
                }
            }
        })
    }

    private fun isLoading(state: Boolean) {
        with(binding) {
            if (state) {
                pbSearchUser.visibility = View.VISIBLE
                tvLoading.visibility = View.VISIBLE
            } else {
                pbSearchUser.visibility = View.INVISIBLE
                tvLoading.visibility = View.INVISIBLE
            }
        }
    }
}
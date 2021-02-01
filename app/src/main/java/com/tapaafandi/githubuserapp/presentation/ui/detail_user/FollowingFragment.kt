package com.tapaafandi.githubuserapp.presentation.ui.detail_user

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tapaafandi.githubuserapp.R
import com.tapaafandi.githubuserapp.adapter.FollowingAdapter
import com.tapaafandi.githubuserapp.databinding.FragmentFollowingBinding
import com.tapaafandi.githubuserapp.domain.model.UserSearch
import com.tapaafandi.githubuserapp.util.Constants.Companion.ARG_USERNAME
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FollowingFragment : Fragment(R.layout.fragment_following) {

    private lateinit var binding: FragmentFollowingBinding
    private lateinit var followingAdapter: FollowingAdapter

    private val viewModel: FollowingViewModel by viewModels()

    companion object {
        fun newInstance(username: String): FollowingFragment {
            val fragment = FollowingFragment()
            val bundle = Bundle()
            bundle.putString(ARG_USERNAME, username)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFollowingBinding.bind(view)

        followingAdapter = FollowingAdapter()

        lifecycleScope.launch {
            requestFollowingData()
            isLoading(true)
        }

        observeFollowingData()
        recyclerViewSetup()
    }

    private fun observeFollowingData() {
        viewModel.followingUser.observe(requireActivity(), { followingItems ->
            if (followingItems != null) {
                followingAdapter.setData(followingItems as ArrayList<UserSearch>)
                isLoading(false)
            }
        })
    }

    private fun recyclerViewSetup() {
        binding.apply {
            rvFollowingUser.layoutManager = LinearLayoutManager(requireContext())
            rvFollowingUser.setHasFixedSize(true)
            rvFollowingUser.adapter = followingAdapter
        }

        followingAdapter.setOnItemClickCallback(object : FollowingAdapter.OnItemClickCallback {
            override fun onItemClicked(data: UserSearch) {
                Intent(requireContext(), DetailUserActivity::class.java).apply {
                    putExtra(DetailUserActivity.EXTRA_DETAIL, data.username)
                    startActivity(this)
                }
            }
        })
    }

    private suspend fun requestFollowingData() {
        val username = arguments?.getString(ARG_USERNAME)
        if (username != null) {
            viewModel.getListFollowingGithubUser(username)
        }
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
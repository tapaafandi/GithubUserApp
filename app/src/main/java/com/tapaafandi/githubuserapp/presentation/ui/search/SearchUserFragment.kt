package com.tapaafandi.githubuserapp.presentation.ui.search

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tapaafandi.githubuserapp.R
import com.tapaafandi.githubuserapp.adapter.SearchUserAdapter
import com.tapaafandi.githubuserapp.databinding.FragmentSearchUserBinding
import com.tapaafandi.githubuserapp.domain.model.UserSearch
import com.tapaafandi.githubuserapp.presentation.ui.detail_user.DetailUserActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchUserFragment : Fragment(R.layout.fragment_search_user) {

    private lateinit var binding: FragmentSearchUserBinding
    private lateinit var searchUserAdapter: SearchUserAdapter

    private val viewModel: SearchUserViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchUserBinding.bind(view)

        searchUserAdapter = SearchUserAdapter()

        observeData()

        binding.etSearchUser.apply {
            setOnEditorActionListener { _, actionId, _ ->
                var isHandled = false
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    lifecycleScope.launch {
                        requestSearchUser()
                    }
                    isHandled = true
                }
                isHandled
            }
        }
        recyclerViewSetup()
    }

    private fun observeData() {
        viewModel.githubUser.observe(viewLifecycleOwner, {
            if (it != null) {
                searchUserAdapter.setData(it)
                isLoading(false, "")
            }
        })
    }

    private suspend fun requestSearchUser() {
        lifecycleScope.launch {
            binding.apply {
                if (etSearchUser.text.isNotBlank()) {
                    val username = etSearchUser.text.toString()
                    isLoading(true, username)
                    viewModel.getListSearchGithubUser(username)
                }
            }
        }
    }

    private fun recyclerViewSetup() {
        binding.rvSearchUserItem.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = searchUserAdapter
        }

        searchUserAdapter.setOnItemClickCallback(object : SearchUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: UserSearch) {
                Intent(requireContext(), DetailUserActivity::class.java).apply {
                    putExtra(DetailUserActivity.EXTRA_DETAIL, data.username)
                    startActivity(this)
                }
            }
        })
    }

    private fun isLoading(state: Boolean, query: String) {
        with(binding) {
            if (state) {
                rvSearchUserItem.visibility = View.INVISIBLE
                pbSearchUser.visibility = View.VISIBLE
                tvSearching.visibility = View.VISIBLE
                tvSearching.text = getString(R.string.searching_for, query)
            } else {
                pbSearchUser.visibility = View.INVISIBLE
                tvSearching.visibility = View.INVISIBLE
                rvSearchUserItem.visibility = View.VISIBLE
            }
        }
    }
}
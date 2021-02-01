package com.tapaafandi.githubuserapp.presentation.ui.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapaafandi.githubuserapp.domain.model.UserSearch
import com.tapaafandi.githubuserapp.domain.repository.GithubUserRepository
import kotlinx.coroutines.launch

class SearchUserViewModel @ViewModelInject constructor(
    private val repository: GithubUserRepository
) : ViewModel() {

    private val _githubUser: MutableLiveData<List<UserSearch>> = MutableLiveData()

    val githubUser: LiveData<List<UserSearch>> get() = _githubUser

    suspend fun getListSearchGithubUser(username: String) {
        viewModelScope.launch {
            val result = repository.searchGithubUser(username)
            _githubUser.value = result
        }
    }
}
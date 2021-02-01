package com.tapaafandi.githubuserapp.presentation.ui.detail_user

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapaafandi.githubuserapp.domain.model.UserSearch
import com.tapaafandi.githubuserapp.domain.repository.GithubUserRepository
import kotlinx.coroutines.launch

class FollowersViewModel @ViewModelInject constructor(
    private val repository: GithubUserRepository
) : ViewModel() {

    private val _followersUser: MutableLiveData<List<UserSearch>> = MutableLiveData()

    val followersUser: LiveData<List<UserSearch>> get() = _followersUser

    suspend fun getListFollowersGithubUser(username: String) {
        viewModelScope.launch {
            val result = repository.getFollowersGithubUser(username)
            _followersUser.value = result
        }
    }
}
package com.tapaafandi.githubuserapp.presentation.ui.detail_user

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapaafandi.githubuserapp.domain.model.UserSearch
import com.tapaafandi.githubuserapp.domain.repository.GithubUserRepository
import kotlinx.coroutines.launch

class FollowingViewModel @ViewModelInject constructor(
    private val repository: GithubUserRepository
) : ViewModel() {

    private val _followingUser: MutableLiveData<List<UserSearch>> = MutableLiveData()

    val followingUser: LiveData<List<UserSearch>> get() = _followingUser

    suspend fun getListFollowingGithubUser(username: String) {
        viewModelScope.launch {
            val result = repository.getFollowingGithubUser(username)
            _followingUser.value = result
        }
    }
}
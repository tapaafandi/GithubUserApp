package com.tapaafandi.githubuserapp.presentation.ui.detail_user

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapaafandi.githubuserapp.domain.model.UserDetail
import com.tapaafandi.githubuserapp.domain.repository.GithubUserRepository
import kotlinx.coroutines.launch

class DetailUserViewModel @ViewModelInject constructor(
    private val repository: GithubUserRepository
) : ViewModel() {

    private val _githubUserDetail: MutableLiveData<UserDetail> = MutableLiveData()
    val githubUser: LiveData<UserDetail> get() = _githubUserDetail

    suspend fun getGithubDetailUser(username: String) {
        viewModelScope.launch {
            val result = repository.getDetailGithubUser(username)
            _githubUserDetail.value = result
        }
    }

    suspend fun addUserToFavorite(userDetail: UserDetail) {
        viewModelScope.launch {
            repository.addFavoriteUser(userDetail)
        }
    }

    suspend fun removeUserFromFavorite(userDetail: UserDetail) {
        viewModelScope.launch {
            repository.deleteUserFavorite(userDetail)
        }
    }

    suspend fun checkFavoriteUser(username: String) = repository.checkUser(username)
}
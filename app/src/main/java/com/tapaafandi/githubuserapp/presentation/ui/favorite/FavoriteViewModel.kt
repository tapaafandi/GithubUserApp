package com.tapaafandi.githubuserapp.presentation.ui.favorite

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.tapaafandi.githubuserapp.domain.repository.GithubUserRepository

class FavoriteViewModel @ViewModelInject constructor(
    repository: GithubUserRepository
) : ViewModel() {

    val favoriteUser = repository.getFavoriteGithubUser().asLiveData()

}
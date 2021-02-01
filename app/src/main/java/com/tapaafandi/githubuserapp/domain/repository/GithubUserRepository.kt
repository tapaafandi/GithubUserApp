package com.tapaafandi.githubuserapp.domain.repository

import com.tapaafandi.githubuserapp.domain.model.UserDetail
import com.tapaafandi.githubuserapp.domain.model.UserSearch
import kotlinx.coroutines.flow.Flow

interface GithubUserRepository {

    suspend fun searchGithubUser(query: String): List<UserSearch>

    suspend fun getDetailGithubUser(username: String): UserDetail

    suspend fun getFollowingGithubUser(username: String): List<UserSearch>

    suspend fun getFollowersGithubUser(username: String): List<UserSearch>

    fun getFavoriteGithubUser(): Flow<List<UserDetail>>

    suspend fun addFavoriteUser(user: UserDetail)

    suspend fun deleteUserFavorite(userDetail : UserDetail)

    suspend fun checkUser(username: String) : List<UserDetail>

}
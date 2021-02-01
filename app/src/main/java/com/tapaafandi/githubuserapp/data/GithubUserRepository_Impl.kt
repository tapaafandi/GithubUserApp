package com.tapaafandi.githubuserapp.data

import com.tapaafandi.githubuserapp.data.local.model.UserFavoriteEntityMapper
import com.tapaafandi.githubuserapp.data.local.room.UserFavoriteDao
import com.tapaafandi.githubuserapp.data.network.GithubApiService
import com.tapaafandi.githubuserapp.data.network.model.UserDetailDtoMapper
import com.tapaafandi.githubuserapp.data.network.model.UserSearchDtoMapper
import com.tapaafandi.githubuserapp.domain.model.UserDetail
import com.tapaafandi.githubuserapp.domain.model.UserSearch
import com.tapaafandi.githubuserapp.domain.repository.GithubUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GithubUserRepository_Impl(
    private val githubApiService: GithubApiService,
    private val userSearchMapper: UserSearchDtoMapper,
    private val userDetailMapper: UserDetailDtoMapper,
    private val userFavoriteEntityMapper: UserFavoriteEntityMapper,
    private val favoriteDao: UserFavoriteDao
) : GithubUserRepository {

    override suspend fun searchGithubUser(query: String): List<UserSearch> {
        return userSearchMapper.toDomainList(githubApiService.getSearchUser(query).items)
    }

    override suspend fun getDetailGithubUser(username: String): UserDetail {
        return userDetailMapper.mapToDomainModel(githubApiService.getDetailUser(username))
    }

    override suspend fun getFollowingGithubUser(username: String): List<UserSearch> {
        return userSearchMapper.toDomainList(githubApiService.getFollowingUserData(username))
    }

    override suspend fun getFollowersGithubUser(username: String): List<UserSearch> {
        return userSearchMapper.toDomainList(githubApiService.getFollowersUserData(username))
    }

    override fun getFavoriteGithubUser(): Flow<List<UserDetail>> {
        return favoriteDao.readFavoriteData().map {
            userFavoriteEntityMapper.toDomainList(it)
        }
    }

    override suspend fun addFavoriteUser(user: UserDetail) {
        val userFavoriteEntity = userFavoriteEntityMapper.mapFromDomainModel(user)
        favoriteDao.insertUserToFavoriteTable(userFavoriteEntity)
    }

    override suspend fun deleteUserFavorite(userDetail: UserDetail) {
        val userFavoriteEntity = userFavoriteEntityMapper.mapFromDomainModel(userDetail)
        favoriteDao.deleteUserFromFavorite(userFavoriteEntity)
    }

    override suspend fun checkUser(username: String): List<UserDetail> {
        return userFavoriteEntityMapper.toDomainList(favoriteDao.checkUserOnDatabase(username))
    }
}
package com.tapaafandi.githubuserapp.di

import com.tapaafandi.githubuserapp.data.GithubUserRepository_Impl
import com.tapaafandi.githubuserapp.data.local.model.UserFavoriteEntityMapper
import com.tapaafandi.githubuserapp.data.local.room.UserFavoriteDao
import com.tapaafandi.githubuserapp.data.network.GithubApiService
import com.tapaafandi.githubuserapp.data.network.model.UserDetailDtoMapper
import com.tapaafandi.githubuserapp.data.network.model.UserSearchDtoMapper
import com.tapaafandi.githubuserapp.domain.repository.GithubUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideGithubUserRepository(
        githubApiService: GithubApiService,
        userSearchDtoMapper: UserSearchDtoMapper,
        userDetailDtoMapper: UserDetailDtoMapper,
        userFavoriteEntityMapper: UserFavoriteEntityMapper,
        favoriteDao: UserFavoriteDao
    ): GithubUserRepository {
        return GithubUserRepository_Impl(
            githubApiService,
            userSearchDtoMapper,
            userDetailDtoMapper,
            userFavoriteEntityMapper,
            favoriteDao,
        )
    }
}
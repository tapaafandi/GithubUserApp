package com.tapaafandi.githubuserapp.di

import com.google.gson.GsonBuilder
import com.tapaafandi.githubuserapp.data.network.GithubApiService
import com.tapaafandi.githubuserapp.data.network.model.UserDetailDtoMapper
import com.tapaafandi.githubuserapp.data.network.model.UserSearchDtoMapper
import com.tapaafandi.githubuserapp.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideUserSearchMapper(): UserSearchDtoMapper {
        return UserSearchDtoMapper()
    }

    @Singleton
    @Provides
    fun provideUserDetailMapper(): UserDetailDtoMapper {
        return UserDetailDtoMapper()
    }

    @Singleton
    @Provides
    fun provideGithubApiService(): GithubApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(GithubApiService::class.java)
    }


}
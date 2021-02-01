package com.tapaafandi.githubuserapp.di

import android.content.Context
import androidx.room.Room
import com.tapaafandi.githubuserapp.data.local.model.UserFavoriteEntityMapper
import com.tapaafandi.githubuserapp.data.local.room.GithubUserDatabase
import com.tapaafandi.githubuserapp.data.local.room.UserFavoriteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {


    @Provides
    fun provideDatabase(@ApplicationContext context: Context): GithubUserDatabase =
        Room.databaseBuilder(context, GithubUserDatabase::class.java, "GithubUser.db")
            .fallbackToDestructiveMigration().build()

    @Provides
    fun provideUserFavoriteDao(database: GithubUserDatabase): UserFavoriteDao =
        database.userFavoriteDao()

    @Singleton
    @Provides
    fun provideUserEntityMapper(): UserFavoriteEntityMapper {
        return UserFavoriteEntityMapper()
    }
}
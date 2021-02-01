package com.tapaafandi.githubuserapp.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tapaafandi.githubuserapp.data.local.model.UserFavoriteEntity

@Database(entities = [UserFavoriteEntity::class], version = 1, exportSchema = false)
abstract class GithubUserDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: GithubUserDatabase? = null

        fun getDatabase(context: Context): GithubUserDatabase? {
            if (INSTANCE != null) {
                synchronized(GithubUserDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext, GithubUserDatabase::class.java, "GithubUser.db"
                    ).build()
                }
            }
            return INSTANCE
        }
    }

    abstract fun userFavoriteDao(): UserFavoriteDao

}
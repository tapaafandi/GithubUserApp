package com.tapaafandi.githubuserapp.data.local.room

import android.database.Cursor
import androidx.room.*
import com.tapaafandi.githubuserapp.data.local.model.UserFavoriteEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface UserFavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserToFavoriteTable(userFavoriteEntity: UserFavoriteEntity)

    @Query("SELECT * FROM favorite_table")
    fun readFavoriteData(): Flow<List<UserFavoriteEntity>>

    @Delete
    suspend fun deleteUserFromFavorite(userFavoriteEntity: UserFavoriteEntity)

    @Query("SELECT * FROM favorite_table WHERE username = :username ")
    suspend fun checkUserOnDatabase(username: String) : List<UserFavoriteEntity>

    @Query("SELECT * FROM favorite_table")
    fun cursorReadFavoriteData(): Cursor
}
package com.tapaafandi.githubuserapp.data.local.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tapaafandi.githubuserapp.util.Constants.Companion.FAVORITE_TABLE

@Entity(tableName = FAVORITE_TABLE)
data class UserFavoriteEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "username")
    var username: String,
    @ColumnInfo(name = "name")
    var name: String?,
    @ColumnInfo(name = "avatar_url")
    var avatarUrl: String?,
    @ColumnInfo(name = "bio")
    var bio: String?,
    @ColumnInfo(name = "company")
    var company: String?,
    @ColumnInfo(name = "location")
    var location: String?,
    @ColumnInfo(name = "repository")
    var repository: Int?,
    @ColumnInfo(name = "following")
    var following: Int?,
    @ColumnInfo(name = "followers")
    var followers: Int?
)
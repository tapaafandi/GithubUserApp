package com.tapaafandi.consumergithubapp

import android.database.Cursor
import com.tapaafandi.consumergithubapp.domain.model.User

object MappingHelper {

    fun mapCursorToArrayList(cursor: Cursor?): ArrayList<User> {
        val userList = ArrayList<User>()
        if (cursor != null) {
            while (cursor.moveToNext()) {
                val username = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumns.USERNAME))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumns.NAME))
                val location = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumns.LOCATION))
                val avatarUrl = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumns.AVATAR_URL))
                userList.add(
                    User(username, name, avatarUrl, location)
                )
            }
        }
        return userList
    }
}
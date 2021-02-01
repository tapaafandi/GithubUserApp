package com.tapaafandi.githubuserapp.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.tapaafandi.githubuserapp.data.local.room.GithubUserDatabase
import com.tapaafandi.githubuserapp.data.local.room.UserFavoriteDao

class UserGithubProvider : ContentProvider() {

    companion object {
        private const val AUTHORITY = "com.tapaafandi.githubuserapp"
        private const val TABLE_NAME = "favorite_table"
        private const val USER = 1
        private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            sUriMatcher.addURI(AUTHORITY, TABLE_NAME, USER)
        }
    }

    private lateinit var userFavoriteDao: UserFavoriteDao

    override fun onCreate(): Boolean {
        userFavoriteDao = GithubUserDatabase.getDatabase(context as Context).userFavoriteDao()
        return false
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        return when (sUriMatcher.match(uri)) {
            USER -> userFavoriteDao.cursorReadFavoriteData()
            else -> null
        }
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return 0
    }
}
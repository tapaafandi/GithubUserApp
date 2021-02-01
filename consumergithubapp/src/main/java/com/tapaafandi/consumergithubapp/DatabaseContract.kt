package com.tapaafandi.consumergithubapp

import android.net.Uri
import android.provider.BaseColumns

object DatabaseContract {

    const val AUTHORITY = "com.tapaafandi.githubuserapp"
    const val SCHEME = "content"

    internal class FavoriteUserColumns: BaseColumns {
        companion object {
            const val TABLE_NAME = "favorite_table"
            const val USERNAME = "username"
            const val NAME = "name"
            const val LOCATION = "location"
            const val AVATAR_URL = "avatar_url"

            val CONTENT_URI = Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build()
        }
    }
}
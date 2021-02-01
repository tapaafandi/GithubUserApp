package com.tapaafandi.githubuserapp.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserSearch(
    var username: String? = "",
    var avatarUrl: String? = "",
): Parcelable
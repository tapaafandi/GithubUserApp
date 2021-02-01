package com.tapaafandi.githubuserapp.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserDetail(
    var username: String? = "",
    var name: String? = "",
    var avatarUrl: String? = "",
    var bio: String? = "",
    var company: String? = "",
    var location: String? = "",
    var repository: Int? = 0,
    var following: Int? = 0,
    var followers: Int? = 0
): Parcelable

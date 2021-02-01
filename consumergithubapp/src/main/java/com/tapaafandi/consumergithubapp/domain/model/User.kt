package com.tapaafandi.consumergithubapp.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var username: String? = "",
    var name: String? = "",
    var avatarUrl: String? = "",
    var location: String? = "",
): Parcelable
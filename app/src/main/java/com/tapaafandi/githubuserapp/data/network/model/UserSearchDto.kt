package com.tapaafandi.githubuserapp.data.network.model


import com.google.gson.annotations.SerializedName

data class UserSearchDto(
    @SerializedName("avatar_url")
    val avatarUrl: String? = "",
    @SerializedName("login")
    val login: String? = "",
)
package com.tapaafandi.githubuserapp.data.network.model


import com.google.gson.annotations.SerializedName

data class UserDetailDto(
    @SerializedName("avatar_url")
    val avatarUrl: String? = "",
    @SerializedName("bio")
    val bio: String? = "",
    @SerializedName("company")
    val company: String? = "",
    @SerializedName("followers")
    val followers: Int? = 0,
    @SerializedName("following")
    val following: Int? = 0,
    @SerializedName("location")
    val location: String? = "",
    @SerializedName("login")
    val login: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("public_repos")
    val publicRepos: Int? = 0,
)
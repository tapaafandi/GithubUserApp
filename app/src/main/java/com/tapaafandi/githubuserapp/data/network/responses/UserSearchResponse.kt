package com.tapaafandi.githubuserapp.data.network.responses

import com.google.gson.annotations.SerializedName
import com.tapaafandi.githubuserapp.data.network.model.UserSearchDto

data class UserSearchResponse(
    @SerializedName("items")
    val items: List<UserSearchDto>,
)
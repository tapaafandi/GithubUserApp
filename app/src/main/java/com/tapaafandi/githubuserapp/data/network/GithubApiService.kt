package com.tapaafandi.githubuserapp.data.network

import com.tapaafandi.githubuserapp.data.network.model.UserDetailDto
import com.tapaafandi.githubuserapp.data.network.model.UserSearchDto
import com.tapaafandi.githubuserapp.data.network.responses.UserSearchResponse
import com.tapaafandi.githubuserapp.util.Constants.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiService {

    @GET("search/users")
    @Headers("Authorization: $API_KEY")
    suspend fun getSearchUser(
        @Query("q") query: String
    ): UserSearchResponse

    @GET("users/{username}")
    @Headers("Authorization: $API_KEY")
    suspend fun getDetailUser(
        @Path("username") username: String
    ): UserDetailDto

    @GET("users/{username}/following")
    @Headers("Authorization: $API_KEY")
    suspend fun getFollowingUserData(
        @Path("username") username: String
    ): List<UserSearchDto>

    @GET("users/{username}/followers")
    @Headers("Authorization: $API_KEY")
    suspend fun getFollowersUserData(
        @Path("username") username: String
    ): List<UserSearchDto>
}
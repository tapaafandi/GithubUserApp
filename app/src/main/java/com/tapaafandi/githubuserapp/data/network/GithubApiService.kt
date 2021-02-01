package com.tapaafandi.githubuserapp.data.network

import com.tapaafandi.githubuserapp.data.network.model.UserDetailDto
import com.tapaafandi.githubuserapp.data.network.model.UserSearchDto
import com.tapaafandi.githubuserapp.data.network.responses.UserSearchResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiService {

    @GET("search/users")
    @Headers("Authorization: token 58005f0bbd09d4afa1f3b7cd58821a0f8ce59bb6")
    suspend fun getSearchUser(
        @Query("q") query: String
    ): UserSearchResponse

    @GET("users/{username}")
    @Headers("Authorization: token 58005f0bbd09d4afa1f3b7cd58821a0f8ce59bb6")
    suspend fun getDetailUser(
        @Path("username") username: String
    ): UserDetailDto

    @GET("users/{username}/following")
    @Headers("Authorization: token 58005f0bbd09d4afa1f3b7cd58821a0f8ce59bb6")
    suspend fun getFollowingUserData(
        @Path("username") username: String
    ): List<UserSearchDto>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 58005f0bbd09d4afa1f3b7cd58821a0f8ce59bb6")
    suspend fun getFollowersUserData(
        @Path("username") username: String
    ): List<UserSearchDto>
}
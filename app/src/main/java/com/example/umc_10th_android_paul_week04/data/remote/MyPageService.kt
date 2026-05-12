package com.example.umc_10th_android_paul_week04.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MyPageService {
    @GET("api/users/1")
    suspend fun getMyInfo(): Response<AuthResponse<UserData>>

    @GET("api/users")
    suspend fun getFollowingList(
        @Query("page") page: Int = 1
    ): Response<AuthResponse<List<UserData>>>
}

data class AuthResponse<T>(
    val data: T,
    val support: Support? = null
)

data class UserData(
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String
)

data class Support(
    val url: String,
    val text: String
)
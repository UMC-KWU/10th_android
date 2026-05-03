package com.example.a10th_umc_week02

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("api/users/1")
    suspend fun getMyInfo(
    ): Response<UserResponse>
    @GET("api/users")
    suspend fun getFollowingList(): Response<FollowingList>
}
package com.example.week03_taro

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReqresService {

    @GET("api/users/{id}")
    suspend fun getUserById(
        @Path("id") userId: Int
    ): Response<ReqresSingleUserResponse>

    @GET("api/users")
    suspend fun getUsers(
        @Query("page") page: Int = 1
    ): Response<ReqresUserListResponse>
}
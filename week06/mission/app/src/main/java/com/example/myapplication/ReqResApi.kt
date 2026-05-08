package com.example.myapplication

import retrofit2.http.*

interface ReqResApi {

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Int): UserResponse

    @GET("users")
    suspend fun getUserList(@Query("page") page: Int = 1): UserListResponse
}
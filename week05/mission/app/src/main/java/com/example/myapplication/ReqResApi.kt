package com.example.myapplication

import retrofit2.Call
import retrofit2.http.*

interface ReqResApi {

    @GET("users/{id}")
    fun getUser(@Path("id") id: Int): Call<UserResponse>

    @GET("users")
    fun getUserList(@Query("page") page: Int = 1): Call<UserListResponse>
}
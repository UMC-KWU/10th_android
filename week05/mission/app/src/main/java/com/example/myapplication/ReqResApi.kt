package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReqResApi {

    @GET("users/{id}")
    fun getUser(@Path("id") id: Int): Call<UserResponse>

    @GET("users")
    fun getUserList(@Query("page") page: Int = 1): Call<UserListResponse>
}
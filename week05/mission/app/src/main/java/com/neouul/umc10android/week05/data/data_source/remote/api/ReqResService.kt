package com.neouul.umc10android.week05.data.data_source.remote.api

import com.neouul.umc10android.week05.data.dto.UserData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ReqResService {
    @GET("api/users")
    suspend fun getUsers(
        @Query("page") page: Int
    ): Response<UserData>
}
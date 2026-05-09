package com.example.a10th_umc_week02.data.remote

import com.example.a10th_umc_week02.data.model.BuyData
import com.example.a10th_umc_week02.data.model.UserData
import com.example.a10th_umc_week02.domain.repository.AuthListResponse
import com.example.a10th_umc_week02.domain.repository.AuthResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("api/products")
    suspend fun getProducts(): Response<List<BuyData>>

    @GET("api/products/{id}")
    suspend fun getProductDetail(
        @Path("id") productId: Int
    ): Response<BuyData>

    @GET("api/myinfo")
    suspend fun getMyInfo(): Response<AuthResponse<UserData>>

    @GET("api/following")
    suspend fun getFollowingList(): Response<AuthListResponse<UserData>>
}
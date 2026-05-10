package com.example.a10th_umc_week02.data.remote

import com.example.a10th_umc_week02.di.DataStoreModule
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val dataStoreModule: DataStoreModule
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val token = runBlocking {
            ""
        }

        val requestBuilder = chain.request().newBuilder()

        if (token.isNotEmpty()) {
            requestBuilder.addHeader("Authorization", "Bearer $token")
        }

        return chain.proceed(requestBuilder.build())
    }
}
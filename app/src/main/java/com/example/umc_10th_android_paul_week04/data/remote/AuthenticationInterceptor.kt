package com.example.umc_10th_android_paul_week04.data.remote

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // 미션에서 제공받은 키
        val apiKey = "reqres_c3c5d9c30d66479183dd032129a8def4"

        // 요청 가로채서 헤더에 x-api-key 추가
        val request = chain.request().newBuilder()
            .addHeader("x-api-key", apiKey)
            .build()

        Log.d("RETROFIT", "Header added: x-api-key")

        return chain.proceed(request)
    }
}
package com.example.week03_taro

import okhttp3.Interceptor
import okhttp3.Response

class ReqresApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val newRequest = originalRequest.newBuilder()
            .addHeader("x-api-key", REQRES_API_KEY)
            .build()

        return chain.proceed(newRequest)
    }

    companion object {
        private const val REQRES_API_KEY = "reqres_700acc063f314d19b74f08911afa5ae6"
    }
}
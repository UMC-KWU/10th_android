package com.example.a10th_umc_week02

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

//retrofit 객체를 생성
object ApiClient {

    //swagger 주소
    private val BASE_URL = "https://reqres.in/" // <- 여기에 실제 Swagger 주소를 넣습니다.
    private const val API_KEY = "reqres_c7dd2e08f6484752b491d89202a23448"
    private val authInterceptor = Interceptor { chain ->
        val original = chain.request()
        val request = original.newBuilder()
            .header("x-api-key", API_KEY)
            .method(original.method, original.body)
            .build()
        chain.proceed(request)
    }
        //로그로 진행상황
        private val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        //클라이언트 생성(로그와 타임아웃 설정)
        private val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

        //retrofit 객체 생성
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        //서비스 정의
        val authService: ApiService = retrofit.create(ApiService::class.java)

    }

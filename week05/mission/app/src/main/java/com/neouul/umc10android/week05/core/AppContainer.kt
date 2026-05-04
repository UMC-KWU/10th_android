package com.neouul.umc10android.week05.core

import android.content.Context
import com.google.gson.Gson
import com.neouul.umc10android.week05.data.data_source.ProductDataSourceImpl
import com.neouul.umc10android.week05.data.data_source.remote.RemoteUserDataSourceImpl
import com.neouul.umc10android.week05.data.data_source.remote.api.ReqResService
import com.neouul.umc10android.week05.data.repository.ProductRepositoryImpl
import com.neouul.umc10android.week05.data.repository.UserRepositoryImpl
import com.neouul.umc10android.week05.data.repository.WishRepositoryImpl
import com.neouul.umc10android.week05.domain.repository.ProductRepository
import com.neouul.umc10android.week05.domain.repository.UserRepository
import com.neouul.umc10android.week05.domain.repository.WishRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class AppContainer(private val context: Context) {
    private val gson = Gson()
    private val BASE_URL = "https://reqres.in/"

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
    val reqResService: ReqResService by lazy {
        retrofit.create(ReqResService::class.java)
    }

    // Data Source
    private val productDataSource by lazy { ProductDataSourceImpl(context) }
    private val userDataSource by lazy { RemoteUserDataSourceImpl(reqResService) }

    // Repository
    val productRepository: ProductRepository by lazy {
        ProductRepositoryImpl(productDataSource, gson)
    }

    val wishRepository: WishRepository by lazy {
        WishRepositoryImpl(productDataSource, gson)
    }

    val userRepository: UserRepository by lazy {
        UserRepositoryImpl(userDataSource)
    }
}
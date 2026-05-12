package com.example.umc_10th_android_paul_week04.di

import com.example.umc_10th_android_paul_week04.data.remote.ApiClient
import com.example.umc_10th_android_paul_week04.data.remote.MyPageService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideMyPageService(): MyPageService = ApiClient.myPageService
}
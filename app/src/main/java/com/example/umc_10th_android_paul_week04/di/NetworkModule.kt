package com.example.umc_10th_android_paul_week04.di

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideMyPageService(): MyPageService = ApiClient.myPageService
}
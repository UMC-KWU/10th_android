package com.example.umc_10th_android_paul_week04.di

import com.example.umc_10th_android_paul_week04.data.repository.HomeRepositoryImpl
import com.example.umc_10th_android_paul_week04.data.repository.UserRepositoryImpl
import com.example.umc_10th_android_paul_week04.data.repository.WishlistRepositoryImpl
import com.example.umc_10th_android_paul_week04.domain.repository.HomeRepository
import com.example.umc_10th_android_paul_week04.domain.repository.UserRepository
import com.example.umc_10th_android_paul_week04.domain.repository.WishlistRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    abstract fun bindHomeRepository(impl: HomeRepositoryImpl): HomeRepository

    @Binds
    @Singleton
    abstract fun bindWishlistRepository(
        wishlistRepositoryImpl: WishlistRepositoryImpl
    ): WishlistRepository
}
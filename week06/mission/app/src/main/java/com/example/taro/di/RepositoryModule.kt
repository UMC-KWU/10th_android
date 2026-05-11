package com.example.taro.di

import com.example.taro.data.repository.ProductLocalRepositoryImpl
import com.example.taro.data.repository.ReqresRemoteRepositoryImpl
import com.example.taro.domain.repository.ProductLocalRepository
import com.example.taro.domain.repository.ReqresRemoteRepository
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
    abstract fun bindProductLocalRepository(
        impl: ProductLocalRepositoryImpl
    ): ProductLocalRepository

    @Binds
    @Singleton
    abstract fun bindReqresRemoteRepository(
        impl: ReqresRemoteRepositoryImpl
    ): ReqresRemoteRepository
}
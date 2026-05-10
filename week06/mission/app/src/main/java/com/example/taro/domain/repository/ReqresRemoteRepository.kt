package com.example.taro.domain.repository

import com.example.taro.data.model.ReqresUserDto

interface ReqresRemoteRepository {
    suspend fun getProfileUser(userId: Int): Result<ReqresUserDto>

    suspend fun getFollowingUsers(page: Int): Result<List<ReqresUserDto>>
}
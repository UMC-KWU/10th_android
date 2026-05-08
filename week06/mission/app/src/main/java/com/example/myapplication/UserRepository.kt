package com.example.myapplication

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val api: ReqResApi
) {
    suspend fun getUser(id: Int): Result<UserResponse> = runCatching {
        api.getUser(id)
    }

    suspend fun getUserList(page: Int = 1): Result<UserListResponse> = runCatching {
        api.getUserList(page)
    }
}

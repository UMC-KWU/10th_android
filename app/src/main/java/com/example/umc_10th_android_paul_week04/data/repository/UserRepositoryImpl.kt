package com.example.umc_10th_android_paul_week04.data.repository

import com.example.umc_10th_android_paul_week04.data.remote.MyPageService
import com.example.umc_10th_android_paul_week04.data.remote.UserData
import com.example.umc_10th_android_paul_week04.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val myPageService: MyPageService
) : UserRepository {

    override suspend fun getMyInfo(): UserData? {
        return try {
            val response = myPageService.getMyInfo()
            if (response.isSuccessful) {
                response.body()?.data
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}
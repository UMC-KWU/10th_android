package com.example.umc_10th_android_paul_week04.domain.repository

import com.example.umc_10th_android_paul_week04.data.remote.UserData

interface UserRepository {
    // 프로필 정보를 가져오는 함수 정의
    suspend fun getMyInfo(): UserData?
}
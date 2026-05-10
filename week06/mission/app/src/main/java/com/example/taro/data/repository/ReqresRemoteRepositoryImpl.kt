package com.example.taro.data.repository

import com.example.taro.data.model.ReqresUserDto
import com.example.taro.data.remote.ReqresService
import com.example.taro.domain.repository.ReqresRemoteRepository
import javax.inject.Inject

class ReqresRemoteRepositoryImpl @Inject constructor(
    private val reqresService: ReqresService
) : ReqresRemoteRepository {

    override suspend fun getProfileUser(userId: Int): Result<ReqresUserDto> {
        return try {
            val response = reqresService.getUser(userId)

            if (response.isSuccessful) {
                val user = response.body()?.data

                if (user != null) {
                    Result.success(user)
                } else {
                    Result.failure(
                        IllegalStateException("프로필 응답 데이터가 비어 있습니다.")
                    )
                }
            } else {
                Result.failure(
                    IllegalStateException("프로필 조회 실패: HTTP ${response.code()}")
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getFollowingUsers(page: Int): Result<List<ReqresUserDto>> {
        return try {
            val response = reqresService.getUsers(page)

            if (response.isSuccessful) {
                val users = response.body()?.data

                if (users != null) {
                    Result.success(users)
                } else {
                    Result.failure(
                        IllegalStateException("팔로잉 응답 데이터가 비어 있습니다.")
                    )
                }
            } else {
                Result.failure(
                    IllegalStateException("팔로잉 목록 조회 실패: HTTP ${response.code()}")
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
package com.example.week03_taro

class ReqresRepository(
    private val service: ReqresService = ReqresApiClient.reqresService
) {

    suspend fun getUserById(userId: Int): Result<ReqresUserDto> {
        return try {
            val response = service.getUserById(userId)

            if (response.isSuccessful) {
                val body = response.body()

                if (body == null) {
                    Result.failure(RuntimeException("유저 응답 body가 비어 있습니다."))
                } else {
                    Result.success(body.data)
                }
            } else {
                Result.failure(
                    RuntimeException("유저 조회 실패: HTTP ${response.code()}")
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getUsers(page: Int = 1): Result<List<ReqresUserDto>> {
        return try {
            val response = service.getUsers(page)

            if (response.isSuccessful) {
                val body = response.body()

                if (body == null) {
                    Result.failure(RuntimeException("유저 리스트 응답 body가 비어 있습니다."))
                } else {
                    Result.success(body.data)
                }
            } else {
                Result.failure(
                    RuntimeException("유저 리스트 조회 실패: HTTP ${response.code()}")
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
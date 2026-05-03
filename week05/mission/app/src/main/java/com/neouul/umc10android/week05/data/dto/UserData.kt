package com.neouul.umc10android.week05.data.dto

import com.google.gson.annotations.SerializedName
import com.neouul.umc10android.week05.domain.model.User

data class UserData(
    @SerializedName("data")
    val data: List<UserDto>? = null,
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
) {
    fun toDomain(): List<User> {
        return data?.map { it.toDomain() } ?: emptyList()
    }
}
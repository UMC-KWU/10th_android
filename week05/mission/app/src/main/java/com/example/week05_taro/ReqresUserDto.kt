package com.example.week03_taro

import com.google.gson.annotations.SerializedName

data class ReqresSingleUserResponse(
    val data: ReqresUserDto
)

data class ReqresUserListResponse(
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    val data: List<ReqresUserDto>
)

data class ReqresUserDto(
    val id: Int,
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val avatar: String
) {
    val fullName: String
        get() = "$firstName $lastName"
}
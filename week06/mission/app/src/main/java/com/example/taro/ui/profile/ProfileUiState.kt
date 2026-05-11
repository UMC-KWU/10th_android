package com.example.taro.ui.profile

import com.example.taro.data.model.ReqresUserDto

data class ProfileUiState(
    val isLoading: Boolean = false,
    val profileUser: ReqresUserDto? = null,
    val followingUsers: List<ReqresUserDto> = emptyList(),
    val errorMessage: String? = null
)
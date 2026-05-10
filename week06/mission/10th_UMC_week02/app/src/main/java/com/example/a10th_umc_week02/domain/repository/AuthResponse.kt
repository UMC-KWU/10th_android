package com.example.a10th_umc_week02.domain.repository

data class AuthResponse<T>(
    val isSuccess: Boolean,
    val code: String,
    val message: String,
    val data: T?
)

data class AuthListResponse<T>(
    val isSuccess: Boolean,
    val code: String,
    val message: String,
    val data: List<T>?
)
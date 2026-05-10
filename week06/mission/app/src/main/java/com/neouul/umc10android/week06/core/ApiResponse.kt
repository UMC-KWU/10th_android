package com.neouul.umc10android.week06.core

data class ApiResponse<T>(
    val status : String,
    val code : String,
    val message : String,
    val data : T? = null,
)
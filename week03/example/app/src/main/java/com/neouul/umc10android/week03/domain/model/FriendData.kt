package com.neouul.umc10android.week03.domain.model

data class FriendData(
    val name: String = "", //이름
    val status: String? = null, //자기소개
    val profileImage: String? = null, //프로필이미지
)
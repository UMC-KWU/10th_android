package com.neouul.umc10android.week06.presentation.fragment.profile

import com.neouul.umc10android.week06.domain.model.User

data class ProfileUiState(
    val users: List<User> = emptyList(),
    val currentUser: User? = null
)
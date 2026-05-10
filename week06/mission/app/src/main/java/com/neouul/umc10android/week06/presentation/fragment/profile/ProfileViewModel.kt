package com.neouul.umc10android.week06.presentation.fragment.profile

import androidx.lifecycle.viewModelScope
import com.neouul.umc10android.week06.core.base.BaseViewModel
import com.neouul.umc10android.week06.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel<ProfileUiState>(ProfileUiState()) {

    private val _errorEvent = MutableSharedFlow<String>()
    val errorEvent: SharedFlow<String> = _errorEvent.asSharedFlow()

    fun loadUserData(token: String) {
        viewModelScope.launch {
            // 1. 사용자 목록 로드
            userRepository.getUsers(page = 1, token = token)
                .onSuccess { userList ->
                    _uiState.update { it.copy(users = userList) }
                }
                .onFailure { e ->
                    _errorEvent.emit("목록 로드 실패: ${e.message}")
                }

            // 2. 특정 사용자 조회 (id = 1)
            userRepository.getUserById(id = 1, token = token)
                .onSuccess { user ->
                    _uiState.update { it.copy(currentUser = user) }
                }
                .onFailure { e ->
                    _errorEvent.emit("개별 정보 로드 실패: ${e.message}")
                }
        }
    }
}


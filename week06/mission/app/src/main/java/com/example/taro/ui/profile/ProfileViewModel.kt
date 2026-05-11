package com.example.taro.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taro.domain.repository.ReqresRemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val reqresRemoteRepository: ReqresRemoteRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        loadProfile()
    }

    fun loadProfile() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null
                )
            }

            val profileResultDeferred = async {
                reqresRemoteRepository.getProfileUser(userId = 1)
            }

            val followingResultDeferred = async {
                reqresRemoteRepository.getFollowingUsers(page = 1)
            }

            val profileResult = profileResultDeferred.await()
            val followingResult = followingResultDeferred.await()

            profileResult.onSuccess { profileUser ->
                _uiState.update {
                    it.copy(profileUser = profileUser)
                }
            }.onFailure { throwable ->
                _uiState.update {
                    it.copy(errorMessage = throwable.message ?: "프로필 정보를 불러오지 못했습니다.")
                }
            }

            followingResult.onSuccess { followingUsers ->
                _uiState.update {
                    it.copy(followingUsers = followingUsers)
                }
            }.onFailure { throwable ->
                _uiState.update {
                    it.copy(errorMessage = throwable.message ?: "팔로잉 목록을 불러오지 못했습니다.")
                }
            }

            _uiState.update {
                it.copy(isLoading = false)
            }
        }
    }

    fun clearErrorMessage() {
        _uiState.update {
            it.copy(errorMessage = null)
        }
    }
}
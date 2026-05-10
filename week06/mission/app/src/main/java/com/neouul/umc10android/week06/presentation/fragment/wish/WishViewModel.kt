package com.neouul.umc10android.week06.presentation.fragment.wish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neouul.umc10android.week06.domain.repository.WishRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class WishViewModel @Inject constructor(
    private val wishRepository: WishRepository
) : ViewModel() {

    val uiState: StateFlow<WishUiState> = wishRepository.getWishedProductsFlow()
        .map { WishUiState(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = WishUiState()
        )
}


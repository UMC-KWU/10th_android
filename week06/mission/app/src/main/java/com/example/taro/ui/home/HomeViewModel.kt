package com.example.taro.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taro.domain.repository.ProductLocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productLocalRepository: ProductLocalRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadHomeProducts()
    }

    private fun loadHomeProducts() {
        viewModelScope.launch {
            productLocalRepository.getHomeProducts().collect { products ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        products = products,
                        errorMessage = null
                    )
                }
            }
        }
    }
}